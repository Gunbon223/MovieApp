package org.gb.movieapp.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Model.Request.LoginRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.entites.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;
    public void login(LoginRequest request){
        //Kiem tra email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new BadRequestException("Email incorrect"));
        //kiem tra xem password co khop khong
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new BadRequestException("Password incorrect");
        }
        //luu thong tin user vao trong session
        session.setAttribute("currentUser",user);
    }

    public void logout(){
        session.removeAttribute("currentUser");
    }
}