package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.Reviews;

import java.util.List;

public interface ReviewService {
    List<Reviews> findByMovies(Movies movies);
}
