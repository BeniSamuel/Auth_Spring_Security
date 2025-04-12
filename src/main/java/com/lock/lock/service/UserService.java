package com.lock.lock.service;

import com.lock.lock.dto.UserInformDto;
import com.lock.lock.exception.NotFoundException;
import com.lock.lock.model.User;
import com.lock.lock.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService ( UserRepository userRepository ) {
        this.userRepository = userRepository;
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
        User newUser = new User(userInformDto.getName(), userInformDto.getEmail(), userInformDto.getPassword());
        return this.userRepository.save(newUser);
    }
}
