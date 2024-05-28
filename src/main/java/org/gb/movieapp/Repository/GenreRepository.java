package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Genre;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
    List<Genre> findByName(String name);
    Genre findById(int id);
}
