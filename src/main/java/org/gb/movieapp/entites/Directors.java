package org.gb.movieapp.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "directors")
public class Directors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String avatar;
    @Column(columnDefinition = "TEXT")
    String bio;
}

