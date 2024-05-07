package org.gb.movieapp.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gb.movieapp.Model.Enum.MovieType;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    Integer releaseYear;
    Double rating;
    @Enumerated(EnumType.STRING)
    MovieType type;
    Boolean status;
    String trailer;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}


