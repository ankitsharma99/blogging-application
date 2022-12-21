package com.backend.bloggapp.security;

import com.backend.bloggapp.entities.User;
import com.backend.bloggapp.exceptions.ResourceNotFoundException;
import com.backend.bloggapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // load user from database by username
        User user = this.userRepository.findByEmailIgnoreCase(username).orElseThrow(()->new ResourceNotFoundException("User", "Username: "+ username, 0));

        return user;
    }
}
