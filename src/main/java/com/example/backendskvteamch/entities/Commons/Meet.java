package com.example.backendskvteamch.entities.Commons;


import com.example.backendskvteamch.entities.Users.Admin;
import com.example.backendskvteamch.entities.Users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "meets")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    private Admin interviewer;


    @ManyToOne
    @JoinColumn(name = "interviewee_id")
    private User interviewee;

    @Column
    private String datetime;

    @Column
    private String caldav_url;

}
