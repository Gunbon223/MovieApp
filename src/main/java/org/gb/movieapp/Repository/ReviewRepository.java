package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews,Integer> {
    List<Reviews> findByMovies(Movies movies);
    List<Reviews> findByMoviesOrderByCreatedAtDesc(Movies movies);
    void deleteByMovies_Id(Integer movieId);

}
