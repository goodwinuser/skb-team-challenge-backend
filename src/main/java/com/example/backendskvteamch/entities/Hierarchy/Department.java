package com.example.backendskvteamch.entities.Hierarchy;

import com.example.backendskvteamch.entities.Users.Admin;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "head_id")
    private Admin head;

    @ToString.Exclude
    @OneToMany(mappedBy = "department", orphanRemoval = true)
    private Set<Admin> employees = new LinkedHashSet<>();

}
