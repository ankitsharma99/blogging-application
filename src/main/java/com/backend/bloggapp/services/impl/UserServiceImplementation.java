package com.backend.bloggapp.services.impl;

import com.backend.bloggapp.entities.User;
import com.backend.bloggapp.exceptions.ResourceNotFoundException;
import com.backend.bloggapp.payloads.UserDto;
import com.backend.bloggapp.repositories.UserRepository;
import com.backend.bloggapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User", " id ", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User", " id ", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return this.userRepository.findAll().stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User", " id ", userId));
        this.userRepository.deleteById(user.getUserId());
    }

    private User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
