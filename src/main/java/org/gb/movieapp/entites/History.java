package org.gb.movieapp.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "histories")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Double duration;
    LocalDateTime created_at;
}
