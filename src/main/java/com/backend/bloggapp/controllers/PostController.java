package com.backend.bloggapp.controllers;

import com.backend.bloggapp.entities.Post;
import com.backend.bloggapp.payloads.ApiResponse;
import com.backend.bloggapp.payloads.CategoryDto;
import com.backend.bloggapp.payloads.PostDto;
import com.backend.bloggapp.payloads.PostResponse;
import com.backend.bloggapp.services.PostService;
import org.apache.coyote.Response;
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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return new ResponseEntity<>(this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long postId) {
        return new ResponseEntity<>(this.postService.getPostById(postId), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") Long postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
    }

    @DeleteMapping("/posts/all")
    public ResponseEntity<ApiResponse> deleteAllPosts() {
        this.postService.deleteAllPosts();
        return new ResponseEntity<>(new ApiResponse("Posts Deleted Successfully", true), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable("postId") Long postId) {
        return new ResponseEntity<>(this.postService.updatePost(postDto, postId), HttpStatus.OK);
    }


    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable("keyword") String keyword) {
        return new ResponseEntity<>(this.postService.searchPosts(keyword), HttpStatus.OK);
    }
}
