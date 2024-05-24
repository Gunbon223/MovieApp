package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Blogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Page<Blogs> findByStatus(boolean status, int size);
    Page<Blogs> findAllByStatus(boolean status,int page, int size);

    List<Blogs> findAllDescCreatedDate();

    List<Blogs> findAllByUser_IdOrderByCreatedAtDesc(int id);

    Blogs getById(int id);
    Blogs getByIdAndSlug(String title, int id);

}
