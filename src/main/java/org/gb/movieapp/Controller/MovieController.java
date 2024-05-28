package org.gb.movieapp.Controller;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Repository.ActorRepository;
import org.gb.movieapp.Repository.CountryRepository;
import org.gb.movieapp.Repository.DirectorRepository;
import org.gb.movieapp.Repository.GenreRepository;
import org.gb.movieapp.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/movie")
public class MovieController {
    private final MovieService movieService;
    private final CountryService countryService;
    private final DirectorService directorRepository;
    private final ActorService actorRepository;
    private final GenreService genreRepository;


    @GetMapping()
    public String getMovieIndex(Model model)
    {
        model.addAttribute("movies",movieService.getAllDesc());
        return "admin/movie/index";
    }

    @GetMapping("/create")
    public String getMovieCreate(Model model)
    {
        //tra ve danh sach quoc gia, dien vien, the loai,loai phim, dao dien
        model.addAttribute("countries",countryService.getAllCountry());
        model.addAttribute("directors",directorRepository.getAllDirectors());
        model.addAttribute("actors",actorRepository.getAllActors());
        model.addAttribute("genres",genreRepository.getAllGenres());
        model.addAttribute("types", MovieType.values());

        return "admin/movie/create";
    }

    @GetMapping("/detail/{id}")
    public String getMovieEdit(Model model, @PathVariable int id)
    {
        model.addAttribute("movie", movieService.getById(id));
        model.addAttribute("countries",countryService.getAllCountry());
        model.addAttribute("directors",directorRepository.getAllDirectors());
        model.addAttribute("actors",actorRepository.getAllActors());
        model.addAttribute("genres",genreRepository.getAllGenres());
        model.addAttribute("types", MovieType.values());

        return "admin/movie/detail";
    }



}
