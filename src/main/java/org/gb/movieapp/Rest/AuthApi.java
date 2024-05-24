package org.gb.movieapp.Rest;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Model.Request.LoginRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Service.AuthService;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
        private final UserRepository userRepository;
        private final HttpSession session;
        private final AuthService authService;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest request){
            authService.login(request);
            return ResponseEntity.ok("Login Successful");
        }

        @PostMapping("/logout")
        public ResponseEntity<?> logout(){
            authService.logout();
            return ResponseEntity.ok("Logout Successful");
        }

    }

