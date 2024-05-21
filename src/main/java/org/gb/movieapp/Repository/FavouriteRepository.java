package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Favourites;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourites,Integer> {
    List<Favourites> findByUser_IdOrderByCreatedAtDesc(int id);

    Favourites findByUser_IdAndMovies_Id(int userId, int movieId);

    Favourites findByUser_Id(int id);
}
