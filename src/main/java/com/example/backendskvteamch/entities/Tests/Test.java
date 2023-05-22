package com.example.backendskvteamch.entities.Tests;


import com.example.backendskvteamch.entities.Users.Admin;
import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tests")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ToString.Exclude
    @OneToMany(mappedBy = "test", orphanRemoval = true)
    private Set<Question> questions = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin creator;

    @ToString.Exclude
    @OneToMany(mappedBy = "test", orphanRemoval = true)
    private Set<TestResult> testResults = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

}
