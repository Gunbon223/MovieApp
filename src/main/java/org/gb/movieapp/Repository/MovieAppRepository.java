package org.gb.movieapp.Repository;

import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieAppRepository extends JpaRepository<Movies,Integer> {
    List<Movies> findByName(String name);
    List<Movies> findByNameIgnoreCase(String name);
    List<Movies> findByNameContaining(String keyword);
    List<Movies> findByNameAndSlug(String name, String slug);
    List<Movies> findByRatingBetween(Double min, Double max);
    List<Movies> findByRatingLessThanEqual(Double max);

    List<Movies> findByTypeOrderByRatingDesc(MovieType type);

    long countByStatus(boolean status);
    boolean existsByName(String name);

    Movies findById(int id);
    Movies findByIdAndSlug(int id, String slug);

    //Phan trang
    Page<Movies> findByTypeAndStatus(MovieType type, boolean status, Pageable pageable);
    Page<Movies> findByStatus(boolean status, Pageable pageable);
    Page<Movies> findByStatusOrderByRatingDesc(Pageable pageable, boolean status);
    Page<Movies> findByType(MovieType type, Pageable pageable);


    List<Movies> findAllByOrderByCreatedAtDesc();

    Optional<Movies> findByIdAndSlugAndStatus(Integer id, String slug, Boolean status);

    int countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Movies> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
