package org.gb.movieapp.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "blogs")
public class Blogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String title;
    String slug;
    @Column(columnDefinition = "TEXT")
    String description;
    @Column(columnDefinition = "TEXT")
    String content;
    String thumbnail;
    Boolean status;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}