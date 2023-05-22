package com.example.backendskvteamch.entities.Tests;


import com.example.backendskvteamch.entities.Users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test_results")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Column
    private Long points;

    @Column
    private boolean cheating;


    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private User candidate;

}
