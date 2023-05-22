package com.example.backendskvteamch.entities.Tests;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "text")
    private String data;

    @ToString.Exclude
    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private Set<Answer> answers = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

}
