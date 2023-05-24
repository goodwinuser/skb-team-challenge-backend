package com.example.backendskvteamch.entities.Vacancies;


import com.example.backendskvteamch.entities.Commons.VacancyType;
import com.example.backendskvteamch.entities.Tests.Test;
import com.example.backendskvteamch.entities.Users.Admin;
import com.example.backendskvteamch.entities.Users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "vacancies")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private VacancyType type;

    @ToString.Exclude
    @ManyToMany(mappedBy = "vacancies")
    private Set<Test> tests = new LinkedHashSet<>();

    private Boolean isOpen;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Admin author;

    @ToString.Exclude
    @OneToMany(mappedBy = "vacancy_inner_list", orphanRemoval = true)
    private Set<User> users = new LinkedHashSet<>();



}
