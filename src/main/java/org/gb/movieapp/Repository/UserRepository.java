package org.gb.movieapp.Repository;

import org.gb.movieapp.Model.Enum.UserRole;
import org.gb.movieapp.entites.Movies;
import org.gb.movieapp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(Enum<UserRole> role);
}
