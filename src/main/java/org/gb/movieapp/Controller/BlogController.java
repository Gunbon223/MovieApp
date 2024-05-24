package org.gb.movieapp.Controller;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Service.BlogService;
import org.gb.movieapp.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/blog")
public class BlogController {
    private final BlogService blogService;
    private final UserService userService;

    @GetMapping("/own-blogs/{id}")
    public String getOwnBlogs(Model model, @PathVariable int id)
    {
        model.addAttribute("blogs",blogService.findAllByUser_IdOrderByCreatedAtDesc(id));
        return "admin/blog/own-blog";
    }
    @GetMapping()
    public String getBlogIndex(Model model)
    {
        model.addAttribute("blogs",blogService.findAllDescCreatedDate());
        return "admin/blog/index";
    }
    @GetMapping("/create")
    public String getBlogCreate()
    {
        return "admin/blog/create";
    }
    @GetMapping("/{id}")
    public String getBlogdetail()
    {
        return "admin/blog/detail";
    }
}
