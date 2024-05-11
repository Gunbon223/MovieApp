package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Blogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Page<Blogs> findByStatus(boolean status, int size);
    Page<Blogs> findAllByStatus(boolean status,int page, int size);

    Blogs getById(int id);
    Blogs getByIdAndSlug(String title, int id);

}
