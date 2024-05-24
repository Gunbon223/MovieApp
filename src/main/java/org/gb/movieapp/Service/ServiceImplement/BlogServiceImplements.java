package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.BlogRepository;
import org.gb.movieapp.Service.BlogService;
import org.gb.movieapp.entites.Blogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImplements implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public Page<Blogs> findByStatus(boolean status,  int size) {
        PageRequest pageRequest = PageRequest.of(1, size, Sort.by("createdAt").descending());
        return blogRepository.findByStatus(status, pageRequest);
    }

    @Override
    public Page<Blogs> findAllByStatus(boolean status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        return blogRepository.findByStatus(status, pageRequest);    }

    @Override
    public List<Blogs> findAllDescCreatedDate() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Blogs> findAllByUser_IdOrderByCreatedAtDesc(int id) {
        return blogRepository.findAllByUser_IdOrderByCreatedAtDesc(id);
    }

    @Override
    public Blogs getById(int id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blogs getByIdAndSlug(String slug, int id) {
        return blogRepository.findByIdAndSlug(id, slug);
    }

}
