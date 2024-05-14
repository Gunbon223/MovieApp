package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Episodes;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EpisodeRepository extends JpaRepository<Episodes,Integer>
{
    List<Episodes> findEpisodesByMovies(Movies movies);
    List<Episodes> findByMovies(Movies movie);

}
