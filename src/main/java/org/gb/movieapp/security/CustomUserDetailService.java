package org.gb.movieapp.security;

import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.entites.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;

    public CustomUserDetailService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(CustomUserDetail::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
