package com.lock.lock.service;

import com.lock.lock.dto.UserInformDto;
import com.lock.lock.exception.NotFoundException;
import com.lock.lock.model.User;
import com.lock.lock.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService ( UserRepository userRepository, PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers () {
        return this.userRepository.findAll();
    }

    public User getUserById (int id) {
        return this.userRepository.findUserById(id).orElseThrow(() -> new NotFoundException("User Not Found!!"));
    }

    public User getUserByEmail (String email) {
        return this.userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("User Not Found@@"));
    }

    public User createUser (UserInformDto userInformDto) {
        User newUser = new User(userInformDto.getName(), userInformDto.getEmail(), passwordEncoder.encode(userInformDto.getPassword()));
        return this.userRepository.save(newUser);
    }


}
