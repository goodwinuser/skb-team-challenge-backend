package com.example.backendskvteamch.entities.Users;


import com.example.backendskvteamch.entities.Commons.HhLink;
import com.example.backendskvteamch.entities.Commons.Meet;
import com.example.backendskvteamch.entities.Commons.Role;
import com.example.backendskvteamch.entities.Tests.TestResult;
import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import com.example.backendskvteamch.utilities.Token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Token> tokens;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidate", orphanRemoval = true)
    private Set<TestResult> testResults = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "interviewee", orphanRemoval = true)
    private Set<Meet> meets = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "hh_link_id")
    private HhLink hhLink;

    @ManyToOne
    @JoinColumn(name = "vacancy_inner_list_id")
    private Vacancy vacancy_inner_list;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
