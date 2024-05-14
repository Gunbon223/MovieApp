package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Country;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}
