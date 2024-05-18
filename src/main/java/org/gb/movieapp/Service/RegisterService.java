package org.gb.movieapp.Service;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Model.Enum.UserRole;
import org.gb.movieapp.Model.Request.RegisterRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Utils.RandomColor;
import org.gb.movieapp.entites.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest) {
        String name = registerRequest.getName();
        String randomColor = RandomColor.getRandomColor();
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new RuntimeException("Password does not match");
        }
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setAvatar("https://placehold.co/600x400/"+randomColor+ "/FFF" + "?text=" + String.valueOf(name.charAt(0)).toUpperCase());
        user.setRole(Enum.valueOf(UserRole.class, "USER"));
        userRepository.save(user);
    }
}
