package com.lock.lock.security;

import com.lock.lock.exception.NotFoundException;
import com.lock.lock.repository.UserRepository;
import com.lock.lock.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomerUserDetailsService ( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByEmail(username).orElseThrow(() -> new NotFoundException("User Not found!!"));
        return new CustomerUserDetails(user);
    }
}
