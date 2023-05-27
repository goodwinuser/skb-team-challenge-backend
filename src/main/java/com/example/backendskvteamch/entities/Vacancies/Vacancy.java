package com.example.backendskvteamch.entities.Vacancies;


import com.example.backendskvteamch.entities.Commons.VacancyType;
import com.example.backendskvteamch.entities.Tests.Test;
import com.example.backendskvteamch.entities.Users.Admin;
import com.example.backendskvteamch.entities.Users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
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

    private Integer minSalary;

    private Integer maxSalary;

    @ToString.Exclude
    @ManyToMany(mappedBy = "vacancies", fetch = FetchType.EAGER)
    private Set<Test> tests = new LinkedHashSet<>();

    private Boolean isOpen;

    @ManyToMany(mappedBy = "vacancies", fetch = FetchType.EAGER)
    private Set<Tag> tags = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Admin author;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(id, vacancy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
