package com.lock.lock.service;

import com.lock.lock.dto.UserInformDto;
import com.lock.lock.dto.UserLoginDto;
import com.lock.lock.exception.NotFoundException;
import com.lock.lock.model.User;
import com.lock.lock.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public User createUser(UserInformDto userInformDto) {
        return this.userService.createUser(userInformDto);
    }

    public String loginUser(UserLoginDto userLoginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
            );
            User user = this.userService.getUserByEmail(userLoginDto.getEmail());
            return jwtUtil.generateToken(user.getEmail());
        } catch (BadCredentialsException e) {
            throw new NotFoundException("Invalid email or password");
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }
}