package com.example.backendskvteamch.services;


import com.example.backendskvteamch.entities.Commons.Role;
import com.example.backendskvteamch.entities.DTO.Auth.AuthResponse;
import com.example.backendskvteamch.entities.DTO.Auth.AuthUNameRequest;
import com.example.backendskvteamch.entities.DTO.Register.RegistrationUserRequestDto;
import com.example.backendskvteamch.entities.Users.Admin;
import com.example.backendskvteamch.entities.Users.User;
import com.example.backendskvteamch.repositories.AdminRepository;
import com.example.backendskvteamch.repositories.TokenRepository;
import com.example.backendskvteamch.repositories.UserRepository;
import com.example.backendskvteamch.utilities.Exceptions.AlreadyExistException;
import com.example.backendskvteamch.utilities.Token.Token;
import com.example.backendskvteamch.utilities.Token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registerUser(RegistrationUserRequestDto request) {
        var existUser = userRepository.findByUsername(request.getUsername());
        if(existUser.isPresent()){
            throw new AlreadyExistException(String.format("Пользователь с username %s уже существует в системе", request.getUsername()));
        }

        var user = User.builder()
                .username(request.getUsername())
                .fio(request.getFio())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse registerAdmin(RegistrationUserRequestDto request) {
        var existAdmin = adminRepository.findByUsername(request.getUsername());
        if(existAdmin.isPresent()){
            throw new AlreadyExistException(String.format("Админ с username %s уже существует в системе", request.getUsername()));
        }


        var admin = Admin.builder()
                .username(request.getUsername())
                .fio(request.getFio())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .last_ip("1")
                .last_logged_in("1")
                .build();

        var savedUser = adminRepository.save(admin);
        var jwtToken = jwtService.generateToken(admin);
        saveAdminToken(savedUser, jwtToken);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticateUser(AuthUNameRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticateAdmin(AuthUNameRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(admin);
        revokeAllAdminsTokens(admin);
        saveAdminToken(admin, jwtToken);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void saveAdminToken(Admin admin, String jwtToken) {
        var token = Token.builder()
                .admin(admin)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void revokeAllAdminsTokens(Admin admin) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(admin.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
