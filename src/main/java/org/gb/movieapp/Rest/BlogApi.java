package org.gb.movieapp.Rest;

import org.gb.movieapp.Model.Request.UpsertBlogRequest;
import org.gb.movieapp.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/blog")
public class BlogApi {
    @Autowired
    BlogService blogService;

    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody UpsertBlogRequest request) {
        blogService.createBlog(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED); //tra ve 201

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@RequestBody UpsertBlogRequest request, @PathVariable int id) {
        blogService.updateBlog(id, request);
        return new ResponseEntity<>(request, HttpStatus.OK); //tra ve 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable int id) {
        blogService.deleteBlog(id);
        return new ResponseEntity<>(HttpStatus.OK); //tra ve 200
    }


}
