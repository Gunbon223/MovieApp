package org.gb.movieapp.Rest;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Model.Request.PasswordRequest;
import org.gb.movieapp.Model.Request.UserInfoRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Service.RegisterService;
import org.gb.movieapp.Service.ServiceImplement.UserServiceImplement;
import org.gb.movieapp.Service.UserService;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {
    @Autowired
    UserServiceImplement userService;

    //cap nhap thong tin user name
    @PutMapping("/changeInfo")
    public ResponseEntity<?> updateUserName(@RequestBody UserInfoRequest request){
        User user = userService.changeInfo(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequest request ){
        userService.changePassword(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }


}
