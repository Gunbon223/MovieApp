package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.GenreRepository;
import org.gb.movieapp.Service.GenreService;
import org.gb.movieapp.entites.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenreServiceImplement implements GenreService {
    @Autowired
    GenreRepository genreRepository;
    @Override
    public List<Genre> getGenresByMovieID(int movieId) {
        return List.of();
    }
}
