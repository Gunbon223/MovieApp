package org.gb.movieapp.Repository;

import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieAppRepository extends JpaRepository<Movies,Integer> {
    List<Movies> findByName(String name);
    List<Movies> findByNameIgnoreCase(String name);
    List<Movies> findByNameContaining(String keyword);
    List<Movies> findByNameAndSlug(String name, String slug);
    List<Movies> findByRatingBetween(Double min, Double max);
    List<Movies> findByRatingLessThanEqual(Double max);

    List<Movies> findByType(MovieType type, Sort sort);
    List<Movies> findByTypeOrderByRatingDesc(MovieType type);

    long countByStatus(boolean status);
    boolean existsByName(String name);

    //Phan trang

    Page<Movies> findByStatus(boolean status, Pageable pageable);
}
