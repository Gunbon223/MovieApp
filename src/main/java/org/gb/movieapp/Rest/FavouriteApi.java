package org.gb.movieapp.Rest;

import org.gb.movieapp.Model.Request.FavouriteMovieRequest;
import org.gb.movieapp.Service.FavouriteService;
import org.gb.movieapp.entites.Favourites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteApi {
    @Autowired
    private FavouriteService favouriteService;

    @PostMapping()
    public ResponseEntity<?> creatNewFavourite(@RequestBody FavouriteMovieRequest request) {
        Favourites favourites = favouriteService.addFavourite(request);
        return new ResponseEntity<>(favourites, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavourite(@PathVariable int id)
    {
        favouriteService.deleteFavouriteByMovieIdAndUserId(id);
        return ResponseEntity.noContent().build(); //tra ve 204

    }

}
