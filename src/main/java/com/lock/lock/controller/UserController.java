package com.lock.lock.controller;

import com.lock.lock.model.User;
import com.lock.lock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lock/user")
public class UserController {
    private final UserService userService;

    public UserController ( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers () {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser (@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUserById(id));
    }
}
