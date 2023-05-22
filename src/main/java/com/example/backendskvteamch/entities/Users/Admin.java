package com.example.backendskvteamch.entities.Users;


import com.example.backendskvteamch.entities.Commons.Comment;
import com.example.backendskvteamch.entities.Commons.Meet;
import com.example.backendskvteamch.entities.Hierarchy.Department;
import com.example.backendskvteamch.entities.Commons.Role;
import com.example.backendskvteamch.entities.Tests.Test;
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
@Table(name = "admins")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String last_logged_in;

    @Column(nullable = false)
    private String last_ip;


    @OneToMany(mappedBy = "admin")
    @ToString.Exclude
    private List<Token> tokens;

    @OneToOne(mappedBy = "head", orphanRemoval = true)
    private Department department_head;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    @Column
    private int org_priority;

    @ToString.Exclude
    @OneToMany(mappedBy = "creator", orphanRemoval = true)
    private Set<Test> tests = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "interviewer", orphanRemoval = true)
    private Set<Meet> meets = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private Set<Vacancy> vacancies = new LinkedHashSet<>();

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
        return username;
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
