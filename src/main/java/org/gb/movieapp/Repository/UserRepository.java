package org.gb.movieapp.Repository;

import org.gb.movieapp.Model.Enum.UserRole;
import org.gb.movieapp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(Enum<UserRole> role);
    Optional<User> findByEmail(String email);
}
