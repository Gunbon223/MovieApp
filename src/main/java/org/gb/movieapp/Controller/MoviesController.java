package org.gb.movieapp.Controller;

import org.gb.movieapp.Repository.MovieAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    MovieAppRepository movieAppRepository;

    @GetMapping()
    public String getAllMovies(Model model,
                               @RequestParam(required = false ,defaultValue = "1") int page,
                               @RequestParam(required = false ,defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
        model.addAttribute("pageData", movieAppRepository.findByStatus(true, pageRequest));
        return "/phimbo";
    }



}
