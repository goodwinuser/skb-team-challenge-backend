package com.example.backendskvteamch.entities.Commons;


import com.example.backendskvteamch.entities.Users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hh_links")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HhLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String link;

    @Column
    private String content;

    @OneToOne(mappedBy = "hhLink")
    private User user;


}
