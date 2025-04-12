package com.lock.lock.service;

import com.lock.lock.dto.UserInformDto;
import com.lock.lock.dto.UserLoginDto;
import org.springframework.stereotype.Service;
import com.lock.lock.model.User;

@Service
public class AuthService {
    private final UserService userService;

    public AuthService (UserService userService) {
        this.userService = userService;
    }

    public User createUser (UserInformDto userInformDto) {
        return this.userService.createUser(userInformDto);
    }

    public String loginUser (UserLoginDto userLoginDto) {
        return "token";
    }
}
