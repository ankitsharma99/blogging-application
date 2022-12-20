package com.backend.bloggapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long postId;
    private String postTitle;
    private String content;
    private String imageName;
    private Date addedDate;
    // the name of the below variables should be category and user ONLY, else the response will return null value
    private CategoryDto category;
    private UserDto user;
}
