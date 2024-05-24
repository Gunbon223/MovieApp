package org.gb.movieapp.Service;

import org.gb.movieapp.Model.Request.PasswordRequest;
import org.gb.movieapp.Model.Request.UserInfoRequest;
import org.gb.movieapp.entites.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {

    void changePassword(PasswordRequest request);

    User changeInfo(int id, String name);

    Optional<User> getUserById(int id);
    User changeAvatar(int id, MultipartFile avatar);

}
