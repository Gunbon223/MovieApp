package org.gb.movieapp.Controller;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Service.*;
import org.gb.movieapp.entites.Blogs;
import org.gb.movieapp.entites.Episodes;
import org.gb.movieapp.entites.Movies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final BlogService blogService;
    private final EpisodeService episodeService;
    private final ReviewService reviewService;
    private final CommentService commentService;
    private final FavouriteService favouriteService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("listTv", movieService.findByTypeAndStatus(MovieType.TVSHOWS,true, 1,  6));
        model.addAttribute("listMovie", movieService.findByTypeAndStatus(MovieType.MOVIE,true, 1,  6));
        model.addAttribute("listFilm", movieService.findByTypeAndStatus(MovieType.FILM,true, 1,  6));
        model.addAttribute("listHotMovie", movieService.OrderByRatingDesc(false, 1, 4));
        model.addAttribute("listBlog", blogService.findByStatus(true,4));
        model.addAttribute("title", "Home");

        return "/web/index";
    }

    @GetMapping("/tvshow")
    public String getTVShows(Model model,
                               @RequestParam(required = false ,defaultValue = "1") int page,
                               @RequestParam(required = false ,defaultValue = "12") int size) {
        model.addAttribute("pageData", movieService.findByTypeAndStatus(MovieType.TVSHOWS,true, page,  size));
        model.addAttribute("currentPage", page);

        return "/web/tvshow";
    }

    @GetMapping("/web/movie")
    public String getMovies(Model model,
                               @RequestParam(required = false ,defaultValue = "1") int page,
                               @RequestParam(required = false ,defaultValue = "12") int size) {
        model.addAttribute("pageData", movieService.findByTypeAndStatus(MovieType.MOVIE,true, page, size));
        model.addAttribute("currentPage", page);

        return "/web/movie";
    }

    @GetMapping("/movietheater")
    public String getMovieTheater(Model model,
                               @RequestParam(required = false ,defaultValue = "1") int page,
                               @RequestParam(required = false ,defaultValue = "12") int size) {
        model.addAttribute("pageData", movieService.findByTypeAndStatus(MovieType.FILM,true, page, size));
        model.addAttribute("currentPage", page);
        return "/web/movietheater";
    }
    @GetMapping("/blog")
    public String getBlog(Model model,
                                  @RequestParam(required = false ,defaultValue = "1") int page,
                                  @RequestParam(required = false ,defaultValue = "10") int size) {
        model.addAttribute("listBlog", blogService.findAllByStatus(true, page, size));
        model.addAttribute("currentPage", page);
        return "/web/blog";
    }


    @GetMapping("/blogdetail/{id}/{slug}")
    public String getBlogDetail(Model model, @PathVariable int id,@PathVariable String slug) {
        model.addAttribute("blog", blogService.getById(id));
        Blogs blogs = blogService.getById(id);
        model.addAttribute("listReview", commentService.getByBLog(blogs));

        return "/web/blogdetail";
    }

    @GetMapping("/moviedetail/{id}/{slug}")
    public String getFilmDetail(Model model, @PathVariable int id,@PathVariable String slug) {
        Movies movie = movieService.getByIdAndSlug(id, slug);
        model.addAttribute("movie", movie);
        model.addAttribute("listReview", reviewService.findByMovies(movie));
        if (movie.getType() == MovieType.TVSHOWS)
            model.addAttribute("listEpisode", episodeService.episodesByMovieId(id));

        if (favouriteService.getFavouriteMovieByUserIdAndMovieId(id) != null) {
            model.addAttribute("isFavourite", true);
        }
        else {
            model.addAttribute("isFavourite", false);
        }

        model.addAttribute("listSameType", movieService.findByTypeAndStatus(movie.getType(), true,1, 4));
        return "/web/filmdetail";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "/web/login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "/web/register";
    }

    @GetMapping("/favourite")
    public String getFavourite(Model model)
    {
        model.addAttribute("listMovie", favouriteService.getFavourite());
        return "/web/favourite";
    }

    @GetMapping("/user-info")
    public String getUserInfo()
    {
        return "/web/userinfo";
    }



    // http://localhost:8080/xem-phim/99/a-passage-to-india?tap=1
    // http://localhost:8080/xem-phim/90/the-sun-also-rises?tap=full
    @GetMapping("/xem-phim/{id}/{slug}")
    public String getXemPhimPage(Model model,
                                 @PathVariable Integer id,
                                 @PathVariable String slug,
                                 @RequestParam String tap) {
        Movies movie = movieService.getMovie(id, slug, true);
        model.addAttribute("relatedMovies", movieService.findByTypeAndStatus(movie.getType(), true,1, 4));
        List<Episodes> episodes = episodeService.getEpisodesByMovieId(id);
        model.addAttribute("movie", movie);
        model.addAttribute("episodes", episodes);
        Episodes currentEpisode = episodeService.getEpisode(id, tap);

        model.addAttribute("currentEpisode", currentEpisode);
        model.addAttribute("listReview", reviewService.findByMovies(movie));

        if (movie.getType() == MovieType.TVSHOWS)
            model.addAttribute("listEpisode", episodeService.episodesByMovieId(id));

        if (favouriteService.getFavouriteMovieByUserIdAndMovieId(id) != null) {
            model.addAttribute("isFavourite", true);
        }
        else {
            model.addAttribute("isFavourite", false);
        }

        return "web/xemphim";
    }




}
