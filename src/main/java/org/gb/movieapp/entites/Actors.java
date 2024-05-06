package org.gb.movieapp.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "actors")
public class Actors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String avatar;
    @Column(columnDefinition = "TEXT")
    String bio;
}

