package org.gb.movieapp.Service.ServiceImplement;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Model.Request.PasswordRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Service.UserService;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.gb.movieapp.Model.Enum.UserRole.ADMIN;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    HttpSession session;
    @Override
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
    @Override
    public User changeInfo(int id, String name) {
        //Kiem tra thong tin
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("Không tìm thấy user"));

        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Tên không được để trống");
        }
        if (name.equals(userRepository.findById(user.getId()).get().getName())) {
            throw new BadRequestException("Tên không được trùng với tên hiện tại");
        }
        //update thong tin
        user.setName(name);
           return userRepository.save(user);

    }
    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findByIdAndRole(id,ADMIN);
    }

    @Override
    public User changeAvatar(int id, MultipartFile avatar) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));

        // Generate a unique name for the file
        String fileName = System.currentTimeMillis() + "_" + avatar.getOriginalFilename();

        // Define the path where the file will be saved
        Path path = Paths.get("src/main/resources/static/user-assets/avatar/" + fileName);

        try {
            // Save the file
            avatar.transferTo(path);
            // Update the user's avatar URL
            user.setAvatar("/user-assets/avatar/" + fileName);
            return userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save avatar", e);
        }
    }


}
