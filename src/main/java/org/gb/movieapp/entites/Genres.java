package org.gb.movieapp.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "genres")
public class Genres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String slug;
}

