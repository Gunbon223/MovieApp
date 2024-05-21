package org.gb.movieapp.Service;

import org.gb.movieapp.Model.Request.FavouriteMovieRequest;
import org.gb.movieapp.entites.Favourites;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FavouriteService
{
    List<Movies> getFavourite();
    List<Movies> getFavouriteMovies(int id);
    Favourites addFavourite(FavouriteMovieRequest request);
    Favourites updateFavourite(FavouriteMovieRequest request, int id);
    void deleteFavourite(int id);

    void deleteFavouriteByMovieIdAndUserId(int id);
}
