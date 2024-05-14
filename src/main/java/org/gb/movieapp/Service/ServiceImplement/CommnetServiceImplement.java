package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.CommentRepository;
import org.gb.movieapp.Service.CommentService;
import org.gb.movieapp.entites.Blogs;
import org.gb.movieapp.entites.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommnetServiceImplement implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public List<Comments> getByBLog(Blogs blogs) {
        return commentRepository.findByBlog(blogs);
    }
}
