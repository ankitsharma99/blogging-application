package com.backend.bloggapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {      // DTO -> Data Transfer Object
    private Long userId;
    private String name;
    private String email;
    private String about;
    private String password;
}
