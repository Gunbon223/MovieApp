package org.gb.movieapp.Rest;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Model.Request.RegisterRequest;
import org.gb.movieapp.Repository.UserRepository;
import org.gb.movieapp.Service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterApi {
    private final UserRepository userRepository;
    private final RegisterService registerService;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        registerService.register(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED); //tra ve 201
    }
}
