package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.DirectorRepository;
import org.gb.movieapp.Service.DirectorService;
import org.gb.movieapp.entites.Directors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DirectorServiceImplement implements DirectorService {
    @Autowired
    DirectorRepository directorRepository;
    @Override
    public List<Directors> getAllDirectors() {
        return directorRepository.findAll();
    }
    @Override
    public Directors getDirectorById(int id) {
        return directorRepository.findById(id);
    }
    @Override
    public List<Directors> getDirectorByIds(Integer[] ids) {
        List<Directors> directors = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ids[i] = ids[i];
            directors.add(getDirectorById(ids[i]));
        }
        return directors;
    }


}
