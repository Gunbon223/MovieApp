package org.gb.movieapp.entites;

import jakarta.persistence.*;
import org.gb.movieapp.Model.Enum.MovieType;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String slug;
    @Column(columnDefinition = "TEXT")
    String description;
    String poster;
    Integer release_year;
    Double rating;
    @Enumerated(EnumType.STRING)
    MovieType type;
    Boolean status;
    String trailer;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}


