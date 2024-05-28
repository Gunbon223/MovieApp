package org.gb.movieapp.Rest;

import lombok.NoArgsConstructor;
import org.gb.movieapp.Model.Request.UpsertMovieRequest;
import org.gb.movieapp.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/movies")
public class MovieApi {
    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody UpsertMovieRequest request) {
        movieService.createMovie(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED); //tra ve 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody UpsertMovieRequest request) {
        movieService.updateMovie(request, id);
        return new ResponseEntity<>(request, HttpStatus.OK); //tra ve 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK); //tra ve 200
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("avatar") MultipartFile avatar, @PathVariable int id) {
        String url = movieService.uploadThumbnail(id,avatar );
        return new ResponseEntity<>(url, HttpStatus.OK); //tra ve 200
    }



}
