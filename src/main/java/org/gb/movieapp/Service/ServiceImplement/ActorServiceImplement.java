package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.ActorRepository;
import org.gb.movieapp.Service.ActorService;
import org.gb.movieapp.entites.Actors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorServiceImplement implements ActorService {
@Autowired
ActorRepository actorRepository;


    @Override
    public List<Actors> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actors getActorById(int id) {
        return actorRepository.findById(id);
    }

    @Override
    public List<Actors> getActorByIds(Integer[] ids) {
        List<Actors> actor =new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ids[i] = ids[i];
            actor.add(getActorById(ids[i]));
        }
        return actor;
    }
}
