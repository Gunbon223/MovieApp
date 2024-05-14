package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Episodes;

import java.util.List;

public interface EpisodeService {
    List<Episodes> episodesByMovieId(int movieId);
}
