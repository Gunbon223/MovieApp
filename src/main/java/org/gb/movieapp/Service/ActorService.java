package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Actors;

import java.util.List;

public interface ActorService {
    List<Actors> getAllActors();

    Actors getActorById(int id);

    List<Actors> getActorByIds(Integer[] ids);
}
