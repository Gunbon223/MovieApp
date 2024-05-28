package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();

    Genre getGenreById(int id);

    List<Genre> getGenreByIds(Integer[] ids);
}
