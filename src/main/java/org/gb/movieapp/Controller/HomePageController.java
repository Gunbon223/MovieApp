package org.gb.movieapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {
    @RequestMapping()
    public String getHomePage() {
        return "index";
    }
}
