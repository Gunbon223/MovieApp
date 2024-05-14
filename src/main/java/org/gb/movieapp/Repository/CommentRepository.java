package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Blogs;
import org.gb.movieapp.entites.Comments;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Integer> {
    List<Comments> findByBlog(Blogs Blog);

}
