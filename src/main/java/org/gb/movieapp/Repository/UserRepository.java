package org.gb.movieapp.Repository;

import org.gb.movieapp.Model.Enum.UserRole;
import org.gb.movieapp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(Enum<UserRole> role);
    Optional<User> findByEmail(String email);
    Optional<User> findByIdAndRole(int id, Enum<UserRole> role);
    List<User> findByCreatedAtBefore(LocalDateTime createdAt);

    List<User> findByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime createdAt2);
}
