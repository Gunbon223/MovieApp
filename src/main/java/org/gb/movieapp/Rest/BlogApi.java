package org.gb.movieapp.Rest;

import org.gb.movieapp.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/blog")
public class BlogApi {
    @Autowired
    BlogService blogService;


}
