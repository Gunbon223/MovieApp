package org.gb.movieapp.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "TEXT")
    String content;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}