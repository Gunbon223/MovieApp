package org.gb.movieapp.Rest;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Model.Request.PasswordRequest;
import org.gb.movieapp.Model.Request.UserInfoRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Service.AuthService;
import org.gb.movieapp.Service.RegisterService;
import org.gb.movieapp.Service.ServiceImplement.UserServiceImplement;
import org.gb.movieapp.Service.UserService;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {
    @Autowired
    UserService userService;
    private final AuthService authService;

    //cap nhap thong tin user name
    @PutMapping("/changeInfo/{id}/{name}")
    public ResponseEntity<?> updateUserName(@PathVariable int id,@PathVariable String name){
        authService.setSessionUser(userService.changeInfo(id, name));
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequest request ){
        userService.changePassword(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    // Update user avatar
    @PutMapping("/changeAvatar/{id}")
    public ResponseEntity<?> updateUserAvatar(@PathVariable int id, @RequestParam("avatar") MultipartFile avatar){
        authService.setSessionUser(userService.changeAvatar(id, avatar));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
