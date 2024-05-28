package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.GenreRepository;
import org.gb.movieapp.Service.GenreService;
import org.gb.movieapp.entites.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GenreServiceImplement implements GenreService {
    @Autowired
    GenreRepository genreRepository;
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(int id) {
        return genreRepository.findById(id);
    }
    @Override
    public List<Genre> getGenreByIds(Integer[] ids) {
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ids[i] = ids[i];
            genres.add(getGenreById(ids[i]));
        }
        return genres;
    }

}
