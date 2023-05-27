package com.example.backendskvteamch.entities.Users;


import com.example.backendskvteamch.entities.Commons.HhLink;
import com.example.backendskvteamch.entities.Commons.Meet;
import com.example.backendskvteamch.entities.Commons.Role;
import com.example.backendskvteamch.entities.Tests.TestResult;
import com.example.backendskvteamch.entities.Vacancies.Tag;
import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import com.example.backendskvteamch.utilities.Token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

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
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    private String thirdName;

    @Column(columnDefinition = "text")
    private String description;

    private Integer expectedSalary;

    private String phone;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Tag> tags = new LinkedHashSet<>();

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

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "vacancy_id")
    private Set<Vacancy> vacancies = new LinkedHashSet<>();;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
