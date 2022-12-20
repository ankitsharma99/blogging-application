package com.backend.bloggapp.services;

import com.backend.bloggapp.entities.Category;
import com.backend.bloggapp.entities.Post;
import com.backend.bloggapp.payloads.PostDto;

import java.util.List;

public interface PostService {
    // create
    PostDto createPost(PostDto postDto, Long userId, Long categoryId);
    // update
    PostDto updatePost(PostDto postDto, Long postId);
    // delete
    void deletePost(Long postId);
    // get one
    PostDto getPostById(Long postId);
    // get all
    List<PostDto> getAllPosts();
    // get all posts by category
    List<PostDto> getAllPostsByCategory(Long categoryId);
    // get all posts by user
    List<Post> getAllPostsByUser(Long userId);
    // search posts
    List<Post> searchPosts(String keyword);
}
