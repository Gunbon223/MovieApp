package org.gb.movieapp.Controller;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Service.BlogService;
import org.gb.movieapp.Service.MovieService;
import org.gb.movieapp.entites.Movies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final BlogService blogService;
    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("listTv", movieService.findByTypeAndStatus(MovieType.TVSHOWS,true, 1,  6));
        model.addAttribute("listMovie", movieService.findByTypeAndStatus(MovieType.MOVIE,true, 1,  6));
        model.addAttribute("listFilm", movieService.findByTypeAndStatus(MovieType.FILM,true, 1,  6));
        model.addAttribute("listHotMovie", movieService.OrderByRatingDesc(false, 1, 4));
        model.addAttribute("listBlog", blogService.findByStatus(true,4));
        return "index";
    }

    @GetMapping("/tvshow")
    public String getTVShows(Model model,
                               @RequestParam(required = false ,defaultValue = "1") int page,
                               @RequestParam(required = false ,defaultValue = "12") int size) {
        model.addAttribute("pageData", movieService.findByTypeAndStatus(MovieType.TVSHOWS,true, page,  size));
        model.addAttribute("currentPage", page);

        return "tvshow";
    }

    @GetMapping("/movie")
    public String getMovies(Model model,
                               @RequestParam(required = false ,defaultValue = "1") int page,
                               @RequestParam(required = false ,defaultValue = "12") int size) {
        model.addAttribute("pageData", movieService.findByTypeAndStatus(MovieType.MOVIE,true, page, size));
        model.addAttribute("currentPage", page);

        return "movie";
    }

    @GetMapping("/movietheater")
    public String getMovieTheater(Model model,
                               @RequestParam(required = false ,defaultValue = "1") int page,
                               @RequestParam(required = false ,defaultValue = "12") int size) {
        model.addAttribute("pageData", movieService.findByTypeAndStatus(MovieType.FILM,true, page, size));
        model.addAttribute("currentPage", page);
        return "movietheater";
    }

    @GetMapping("/blogdetail/{id}")
    public String getBlogDetail(Model model, @PathVariable int id) {
        model.addAttribute("blog", blogService.getById(id));
        return "blogdetail";
    }

    @GetMapping("/moviedetail/{id}")
    public String getFilmDetail(Model model, @PathVariable int id) {
        Movies movie = movieService.getById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("listSameType", movieService.findByTypeAndStatus(movie.getType(), true,1, 4));
        return "filmdetail";
    }


}
