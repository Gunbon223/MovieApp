package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Directors;

import java.util.List;

public interface DirectorService
{
    List<Directors> getAllDirectors();

    Directors getDirectorById(int id);

    List<Directors> getDirectorByIds(Integer[] ids);
}
