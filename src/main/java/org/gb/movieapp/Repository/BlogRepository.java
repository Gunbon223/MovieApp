package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Blogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blogs,Integer> {
    Page<Blogs> findByStatus(boolean status, Pageable pageable);
    Blogs findById(int id);
    Blogs findByIdAndSlug(int id, String slug);
    Blogs findByTitleAndId(String title, int id);
}
