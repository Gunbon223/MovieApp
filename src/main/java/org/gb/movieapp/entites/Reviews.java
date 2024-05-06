package org.gb.movieapp.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Reviews{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "TEXT")
    String content;
    Integer rating;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}
