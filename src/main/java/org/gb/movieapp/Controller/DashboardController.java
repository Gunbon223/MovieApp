package org.gb.movieapp.Controller;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Service.BlogService;
import org.gb.movieapp.Service.MovieService;
import org.gb.movieapp.Service.UserService;
import org.gb.movieapp.entites.Blogs;
import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

import static org.thymeleaf.util.DateUtils.year;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/dashboard")
public class DashboardController {
    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    MovieService movieService;


    @GetMapping()
    public String getAdminPage(Model model) {
        model.addAttribute("userCount", userService.getUserCount().size());
        model.addAttribute("userMapCountByMonth", userService.getUserbyMonth(LocalDateTime.now().getYear()));
        List<User> currentMonthUsers = userService.getUserbyMonth(LocalDateTime.now().getYear()).get(LocalDateTime.now().getMonth().getValue());
        model.addAttribute("currentMonthUsers", currentMonthUsers);

        model.addAttribute("movieCount", movieService.getMovieCount());
        model.addAttribute("movieMapCountByMonth", movieService.getMovieCountByMonth(LocalDateTime.now().getYear()));
        List<Movies> currentMonthMovies = movieService.getMovieCountByMonth(LocalDateTime.now().getYear()).get(LocalDateTime.now().getMonth().getValue());
        model.addAttribute("currentMonthMovies", currentMonthMovies);


        model.addAttribute("blogCount", blogService.getBlogCount());
        model.addAttribute("blogMapCountByMonth", blogService.getBlogCountByMonth(LocalDateTime.now().getYear()));
        List<Blogs> currentMonthBlogs = blogService.getBlogCountByMonth(LocalDateTime.now().getYear()).get(LocalDateTime.now().getMonth().getValue());
        model.addAttribute("currentMonthBlogs", currentMonthBlogs);

        return "/admin/dashboard/dashboard";
    }
}
