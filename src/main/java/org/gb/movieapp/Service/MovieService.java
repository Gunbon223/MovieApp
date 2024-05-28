package org.gb.movieapp.Service;

import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Model.Request.UpsertMovieRequest;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {
    Movies getById(int id);
    Movies getByIdAndSlug(int id, String slug);
    //Phan trang
    Page<Movies> findByTypeAndStatus(MovieType type, boolean status, int page, int size);
    Page<Movies> findByStatus(boolean status, Pageable pageable);
    Page<Movies> OrderByRatingDesc(boolean status,int page, int size);
    List<Movies> getAllDesc();

    Movies createMovie(UpsertMovieRequest request);

    Movies updateMovie(UpsertMovieRequest request, int id);

    @Transactional
    void deleteMovie(Integer movieId);

    String uploadThumbnail(int id, MultipartFile avatar);
}
