package org.gb.movieapp.Service;

import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    Movies getById(int id);
    Movies getByIdAndSlug(int id, String slug);
    //Phan trang
    Page<Movies> findByTypeAndStatus(MovieType type, boolean status, int page, int size);
    Page<Movies> findByStatus(boolean status, Pageable pageable);
    Page<Movies> OrderByRatingDesc(boolean status,int page, int size);
}
