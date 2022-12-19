package com.backend.bloggapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {      // DTO -> Data Transfer Object
    private Long userId;
    @NotEmpty
    @Size(min = 4, max = 20, message = "Username must be minimum of 4 and maximum of 20 characters")
    private String name;
    @Email(message = "Please check your E-mail format")
    private String email;
    @NotEmpty
    private String about;
    @NotEmpty
    @Size(min = 8, max = 16, message = "Password must be minimum of 8 characters and maximum of 16 characters")
    private String password;
}
