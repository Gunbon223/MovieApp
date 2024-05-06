package org.gb.movieapp.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    LocalDateTime created_at;
}