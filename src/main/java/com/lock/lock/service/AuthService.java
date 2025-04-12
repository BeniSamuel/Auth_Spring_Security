package com.lock.lock.service;

import com.lock.lock.dto.UserInformDto;
import com.lock.lock.dto.UserLoginDto;
import com.lock.lock.exception.UnAuthorizedException;
import com.lock.lock.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.lock.lock.model.User;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService (UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User createUser (UserInformDto userInformDto) {
        return this.userService.createUser(userInformDto);
    }

    public String loginUser (UserLoginDto userLoginDto) {
        User user = this.userService.getUserByEmail(userLoginDto.getEmail());
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new UnAuthorizedException("Invalid Credentials");
        }
        return jwtUtil.generateToken(user.getEmail());
    }
}
