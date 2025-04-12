package com.lock.lock.controller;

import com.lock.lock.dto.UserInformDto;
import com.lock.lock.dto.UserLoginDto;
import com.lock.lock.model.User;
import com.lock.lock.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lock/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController ( AuthService authService ) {
        this.authService = authService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<User> registerUser (@RequestBody UserInformDto userInformDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.createUser(userInformDto));
    }

    @PostMapping("/login-user")
    public ResponseEntity<String> loginUser (@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.loginUser(userLoginDto));
    }
}
