package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.EpisodeRepository;
import org.gb.movieapp.Repository.MovieAppRepository;
import org.gb.movieapp.Service.EpisodeService;
import org.gb.movieapp.entites.Episodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EpisodeImplements implements EpisodeService {
    @Autowired
    EpisodeRepository episodesRepository;
    @Autowired
    MovieAppRepository movieAppRepository;

    @Override
    public List<Episodes> episodesByMovieId(int movieId) {
        return episodesRepository.findEpisodesByMovies(movieAppRepository.findById(movieId));
    }
}
