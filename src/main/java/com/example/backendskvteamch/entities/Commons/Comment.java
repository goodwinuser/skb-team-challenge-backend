package com.example.backendskvteamch.entities.Commons;

import com.example.backendskvteamch.entities.Users.Admin;
import com.example.backendskvteamch.entities.Users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String content;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Admin author;


}
