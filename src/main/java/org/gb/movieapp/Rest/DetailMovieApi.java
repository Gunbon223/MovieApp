package org.gb.movieapp.Rest;

import org.gb.movieapp.Model.Request.UpsertReviewRequest;
import org.gb.movieapp.Service.ReviewService;
import org.gb.movieapp.entites.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class DetailMovieApi {
    @Autowired
    private ReviewService reviewService;

    //tao review
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody UpsertReviewRequest request){
        Reviews reviews = reviewService.createReview(request);
        return new ResponseEntity<>(reviews, HttpStatus.CREATED); //tra ve 201
    }
    //cap nhat review
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpsertReviewRequest request, @PathVariable int id){
        Reviews reviews = reviewService.updateReview(request, id);
        return ResponseEntity.ok(reviews); //tra ve 200
    }


    //xoa review
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build(); //tra ve 204
    }
}
