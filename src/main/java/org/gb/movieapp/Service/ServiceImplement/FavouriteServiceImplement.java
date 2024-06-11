package org.gb.movieapp.Service.ServiceImplement;

import jakarta.servlet.http.HttpSession;
import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Exception.ResourceNotFoundException;
import org.gb.movieapp.Model.Request.FavouriteMovieRequest;
import org.gb.movieapp.Repository.FavouriteRepository;
import org.gb.movieapp.Repository.MovieAppRepository;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Service.FavouriteService;
import org.gb.movieapp.entites.Favourites;
import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteServiceImplement implements FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    MovieAppRepository movieRes;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HttpSession session;


    @Override
    public List<Movies> getFavourite() {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user"));

        List<Movies> movies = new ArrayList<>();
        for (Favourites favourite : favouriteRepository.findAll()) {
            if (favourite.getUser().getId().equals(user.getId())) {
                movies.add(favourite.getMovies());
            }
        }
        return movies;
    }

    @Override
    public List<Movies> getFavouriteMovies(int id) {
        return null;
    }

    @Override
    public Favourites addFavourite(FavouriteMovieRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user"));

        if (user == null) {
            throw new BadRequestException("Bạn cần đăng nhập để thực hiện chức năng này");
        }
        Movies movie = movieRes.findById(request.getMovieId());
        if (movie == null) {
            throw new BadRequestException("Không tìm thấy phim này !");
        }
        if (favouriteRepository.findByUser_IdAndMovies_Id(user.getId(), request.getMovieId()) != null) {
            throw new BadRequestException("Phim đã được thêm vào yêu thích");
        }


        Favourites favourites = Favourites.builder()
                .user(user)
                .movies(movie)
                .createdAt(java.time.LocalDateTime.now())
                .build();

        return favouriteRepository.save(favourites);
    }

    @Override
    public Favourites updateFavourite(FavouriteMovieRequest request, int id) {
        return null;
    }

    @Override
    public void deleteFavourite(int id) {

    }
    @Override
    public Favourites getFavouriteMovieByUserIdAndMovieId(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElse(null);
        if (user == null) {
            return null;
        }

        Favourites favourites = favouriteRepository.findByUser_IdAndMovies_Id(user.getId(), id);
        return favourites;
    }


    @Override
    public void deleteFavouriteByMovieIdAndUserId(int MovieId) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user"));

        Favourites favourites = favouriteRepository.findByUser_IdAndMovies_Id(user.getId(),MovieId);
        if (favourites == null) {
            throw new BadRequestException("Phim chưa đươc thêm vào yêu thích nên không thể xoá");
        } else if (!favourites.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You cannot delete someone else's favourite movie");
        } else {
            favouriteRepository.deleteById(favourites.getId());
        }
    }
}

