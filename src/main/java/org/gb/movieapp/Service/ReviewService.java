package org.gb.movieapp.Service;

import org.gb.movieapp.Model.Request.UpsertReviewRequest;
import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.Reviews;

import java.util.List;

public interface ReviewService {
    List<Reviews> findByMovies(Movies movies);

    Reviews createReview(UpsertReviewRequest request);

    Reviews updateReview(UpsertReviewRequest request, int id);

    void deleteReview(int id);
}
