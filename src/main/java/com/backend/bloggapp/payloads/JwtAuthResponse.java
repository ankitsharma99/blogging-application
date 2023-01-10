package com.backend.bloggapp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {
    private String token;
}
