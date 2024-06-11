package org.gb.movieapp.Service.ServiceImplement;

import jakarta.servlet.http.HttpSession;
import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Exception.ResourceNotFoundException;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    HttpSession session;

    @Override
    public List<Reviews> findByMovies(Movies movies) {
        return reviewRepository.findByMoviesOrderByCreatedAtDesc(movies);
    }

    //Validate thong tin: rating, content, su dung validationF
    @Override
    public Reviews createReview(UpsertReviewRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user"));
        if (user == null) {
            throw new BadRequestException("Bạn cần đăng nhập để thực hiện chức năng này");
        }

        Movies movie = movieRes.findById(request.getMovieId()).orElseThrow(() -> new BadRequestException("Không tìm thấy phim này !"));

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
        Reviews reviews = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy review"));
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user"));

        Movies movie = movieRes.findById(request.getMovieId()).orElseThrow(() -> new BadRequestException("Không tìm thấy phim này !"));

        //check if user is the owner of the review
        if(!reviews.getUser().getId().equals(user.getId())){
            throw new BadRequestException("Bạn không thể chỉnh sửa review của người khác");
        }

        //check if the review is already on the movie
        if(!reviews.getMovies().getId().equals(movie.getId()) ){
            throw new BadRequestException("Review không thuộc phim này");
        }

        reviews.setContent(request.getContent());
        reviews.setRating(request.getRating());
        reviews.setMovies(movie);
        reviews.setUpdatedAt(java.time.LocalDateTime.now());
        return reviewRepository.save(reviews);

    }

    @Override
    public void deleteReview(int id) {
        Reviews reviews = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy review"));

Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user"));

        //check if user is the owner of the review
        if(!reviews.getUser().getId().equals(user.getId())){
            throw new ResourceNotFoundException("Bạn không thể chỉnh sửa review của người khác");
        }
        reviewRepository.delete(reviews);
    }
}
