package org.gb.movieapp.entites;


import jakarta.persistence.*;
import org.gb.movieapp.Model.Enum.UserRole;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false, unique = true)
    String email;
    String avatar;
    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
