package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Repository.MovieAppRepository;
import org.gb.movieapp.Service.MovieService;
import org.gb.movieapp.entites.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplement implements MovieService
{
    @Autowired
    MovieAppRepository movieAppRepository;

    @Override
    public Movies getById(int id) {
        return movieAppRepository.findById(id);
    }

    @Override
    public Page<Movies> findByTypeAndStatus(MovieType type, boolean status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by("CreatedAt").descending());
        return movieAppRepository.findByTypeAndStatus(type, status, pageRequest);
    }

    @Override
    public Page<Movies> findByStatus(boolean status, Pageable pageable) {
        return movieAppRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Movies> OrderByRatingDesc(boolean status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("CreatedAt").descending());
        return movieAppRepository.findByStatusOrderByRatingDesc(pageRequest, status);
    }
}
