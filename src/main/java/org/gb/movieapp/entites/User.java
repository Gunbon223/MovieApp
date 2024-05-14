package org.gb.movieapp.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gb.movieapp.Model.Enum.UserRole;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
