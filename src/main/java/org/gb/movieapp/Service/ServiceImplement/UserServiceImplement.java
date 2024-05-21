package org.gb.movieapp.Service.ServiceImplement;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Model.Request.PasswordRequest;
import org.gb.movieapp.Model.Request.UserInfoRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Service.UserService;
import org.gb.movieapp.entites.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserServiceImplement{
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;
    HttpSession session;

    public void changePassword(PasswordRequest request) {
        //Kiem tra password cu
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            throw new BadRequestException("Bạn cần đăng nhập để thực hiện chức năng này");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Mật khẩu cũ không đúng");
        }
        //update password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

    }

    public User changeInfo(UserInfoRequest request) {
        //Kiem tra thong tin
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            throw new BadRequestException("Bạn cần đăng nhập để thực hiện chức năng này");
        }
        //update thong tin
        user.setName(request.getName());
        return userRepository.save(user);

    }


}
