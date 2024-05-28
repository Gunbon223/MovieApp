package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Directors;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DirectorRepository extends JpaRepository<Directors,Integer> {
    Directors findByName(String name);
    Directors findById(int id);
}
