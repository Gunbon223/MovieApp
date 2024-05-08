package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Model.Enum.MovieType;
import org.gb.movieapp.Repository.MovieAppRepository;
import org.gb.movieapp.Service.TvShowService;
import org.gb.movieapp.entites.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvShowServiceImplement implements TvShowService
{
    @Autowired
    MovieAppRepository movieAppRepository;

    @Override
    public List<Movies> findByName(String name) {
        return movieAppRepository.findByName(name);
    }

    @Override
    public List<Movies> findByNameIgnoreCase(String name) {
        return movieAppRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Movies> findByNameContaining(String keyword) {
        return movieAppRepository.findByNameContaining(keyword);
    }

    @Override
    public List<Movies> findByNameAndSlug(String name, String slug) {
        return movieAppRepository.findByNameAndSlug(name, slug);
    }

    @Override
    public List<Movies> findByRatingBetween(Double min, Double max) {
        return movieAppRepository.findByRatingBetween(min, max);
    }

    @Override
    public List<Movies> findByRatingLessThanEqual(Double max) {
        return movieAppRepository.findByRatingLessThanEqual(max);
    }

    @Override
    public Page<Movies> findByType(MovieType type, Pageable sort) {
        return movieAppRepository.findByType(type, sort);
    }

    @Override
    public List<Movies> findByTypeOrderByRatingDesc(MovieType type) {
        return movieAppRepository.findByTypeOrderByRatingDesc(type);
    }

    @Override
    public long countByStatus(boolean status) {
        return movieAppRepository.countByStatus(status);
    }

    @Override
    public boolean existsByName(String name) {
        return movieAppRepository.existsByName(name);
    }

    @Override
    public Page<Movies> findByTypeAndStatus(MovieType type, boolean status, Pageable pageable) {
        return movieAppRepository.findByTypeAndStatus(type, status, pageable);
    }

    @Override
    public Page<Movies> findByStatus(boolean status, Pageable pageable) {
        return movieAppRepository.findByStatus(status, pageable);
    }
}
