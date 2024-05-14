package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Favourites;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FavouriteRepository extends JpaRepository<Favourites,Integer> {
}
