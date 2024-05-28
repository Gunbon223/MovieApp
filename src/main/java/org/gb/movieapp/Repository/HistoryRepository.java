package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.History;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HistoryRepository extends JpaRepository<History,Integer> {

    void deleteByMovies_Id(Integer movieId);

}
