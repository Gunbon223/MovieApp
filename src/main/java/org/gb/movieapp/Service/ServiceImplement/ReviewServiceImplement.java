package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Model.Request.UpsertReviewRequest;
import org.gb.movieapp.Repository.MovieAppRepository;
import org.gb.movieapp.Repository.ReviewRepository;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Service.MovieService;
import org.gb.movieapp.Service.ReviewService;
import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.Reviews;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplement implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MovieAppRepository movieRes;
    @Autowired
    UserRepository userRepository;


    @Override
    public List<Reviews> findByMovies(Movies movies) {
        return reviewRepository.findByMoviesOrderByCreatedAtDesc(movies);
    }

    //Validate thong tin: rating, content, su dung validation
    @Override
    public Reviews createReview(UpsertReviewRequest request) {
        //TODO: fix userid la user dang login
        Integer userId = 1;

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Movies movie = movieRes.findById(request.getMovieId()).orElseThrow(() -> new RuntimeException("Movie not found"));

        //tao review
        Reviews reviews = Reviews.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .user(user)
                .movies(movie)
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build();
        return reviewRepository.save(reviews);
    }

    @Override
    public Reviews updateReview(UpsertReviewRequest request, int id) {
        Reviews reviews = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));

        Integer userId = 1;
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Movies movie = movieRes.findById(request.getMovieId()).orElseThrow(() -> new RuntimeException("Movie not found"));

        //check if user is the owner of the review
        if(!reviews.getUser().getId().equals(user.getId())){
            throw new RuntimeException("You are not the owner of this review");
        }

        //check if the review is already on the movie
        if(!reviews.getMovies().getId().equals(movie.getId()) ){
            throw new RuntimeException("This review is not on the movie");
        }

        reviews.setContent(request.getContent());
        reviews.setRating(request.getRating());
        reviews.setMovies(movie);
        reviews.setUpdatedAt(java.time.LocalDateTime.now());

        return reviewRepository.save(reviews);


    }

    @Override
    public void deleteReview(int id) {
        Reviews reviews = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));

        Integer userId = 1;
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        //check if user is the owner of the review
        if(!reviews.getUser().getId().equals(user.getId())){
            throw new RuntimeException("You are not the owner of this review");
        }

        reviewRepository.delete(reviews);
    }
}
