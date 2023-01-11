package com.backend.bloggapp.controllers;

import com.backend.bloggapp.entities.User;
import com.backend.bloggapp.payloads.ApiResponse;
import com.backend.bloggapp.payloads.UserDto;
import com.backend.bloggapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping ("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody  UserDto userDto) {
        UserDto userDto1 = this.userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Long userId) {
        UserDto userDto1 = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("userId") Long userId) {
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);
    }

}
