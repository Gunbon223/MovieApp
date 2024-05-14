package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Blogs;
import org.gb.movieapp.entites.Comments;
import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.Reviews;

import java.util.List;

public interface CommentService {
    List<Comments> getByBLog(Blogs blogs);

}
