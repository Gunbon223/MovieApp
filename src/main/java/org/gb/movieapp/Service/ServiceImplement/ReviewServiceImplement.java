package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.ReviewRepository;
import org.gb.movieapp.Service.ReviewService;
import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplement implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Reviews> findByMovies(Movies movies) {
        return reviewRepository.findByMovies(movies);
    }
}
