package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Episodes;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EpisodeRepository extends JpaRepository<Episodes,Integer>
{
    List<Episodes> findEpisodesByMovies(Movies movies);
    List<Episodes> findByMovies_Id(int movieId);
    Episodes findByMovies_IdAndOrders(int movieId, int order);
    Episodes findById(int id);

    void deleteByMovies_Id(Integer movieId);
}
