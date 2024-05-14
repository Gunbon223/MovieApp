package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getGenresByMovieID(int movieId);
}
