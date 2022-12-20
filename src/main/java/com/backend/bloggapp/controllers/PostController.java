package com.backend.bloggapp.controllers;

import com.backend.bloggapp.entities.Post;
import com.backend.bloggapp.payloads.CategoryDto;
import com.backend.bloggapp.payloads.PostDto;
import com.backend.bloggapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable("userId") Long userId,
                                              @PathVariable("categoryId") Long categoryId) {
        return new ResponseEntity<>(this.postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(this.postService.getAllPostsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable("categoryId") Long categoryId) {
        return new ResponseEntity<>(this.postService.getAllPostsByCategory(categoryId), HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(this.postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long postId) {
        return new ResponseEntity<>(this.postService.getPostById(postId), HttpStatus.OK);
    }
}
