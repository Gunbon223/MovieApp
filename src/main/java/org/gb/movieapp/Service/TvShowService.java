package org.gb.movieapp.Service;

import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
public interface TvShowService {
    List<Movies> findByName(String name);
    List<Movies> findByNameIgnoreCase(String name);
    List<Movies> findByNameContaining(String keyword);
    List<Movies> findByNameAndSlug(String name, String slug);
    List<Movies> findByRatingBetween(Double min, Double max);
    List<Movies> findByRatingLessThanEqual(Double max);

    Page<Movies> findByType(MovieType type, Pageable pageable);
    List<Movies> findByTypeOrderByRatingDesc(MovieType type);

    long countByStatus(boolean status);
    boolean existsByName(String name);

    //Phan trang
    Page<Movies> findByTypeAndStatus(MovieType type, boolean status, Pageable pageable);
    Page<Movies> findByStatus(boolean status, Pageable pageable);
}
