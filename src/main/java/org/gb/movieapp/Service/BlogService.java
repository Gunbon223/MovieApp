package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Blogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Page<Blogs> findByStatus(boolean status, int size);
    Blogs getById(int id);
    Blogs getByTitleAndId(String title, int id);

}
