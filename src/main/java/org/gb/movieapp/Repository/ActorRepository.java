package org.gb.movieapp.Repository;

import org.gb.movieapp.entites.Actors;
import org.gb.movieapp.entites.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ActorRepository extends JpaRepository<Actors,Integer> {
}
