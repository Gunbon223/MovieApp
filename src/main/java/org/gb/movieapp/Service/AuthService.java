package org.gb.movieapp.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Model.Request.LoginRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;
    @Autowired
    AuthenticationManager authenticationManager;
    public void login(LoginRequest request){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            session.setAttribute("currentUser", authenticate);
        } catch (DisabledException d)
        {
            throw new BadRequestException("Account is disabled");
        } catch (AuthenticationException e) {
            throw new BadRequestException("Invalid email/password");
        }
    }

    public void logout(){
        session.removeAttribute("currentUser");
    }

    public User getCurrentUser(){
        return (User) session.getAttribute("currentUser");
    }

    public void setSessionUser(User user){
        session.setAttribute("currentUser",user);
    }

}