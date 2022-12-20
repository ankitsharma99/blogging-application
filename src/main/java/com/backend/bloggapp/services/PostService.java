package com.backend.bloggapp.services;

import com.backend.bloggapp.entities.Category;
import com.backend.bloggapp.entities.Post;
import com.backend.bloggapp.payloads.PostDto;
import com.backend.bloggapp.payloads.PostResponse;

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
    PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
    // get all posts by category
    List<PostDto> getAllPostsByCategory(Long categoryId);
    // get all posts by user
    List<PostDto> getAllPostsByUser(Long userId);
    // search posts
    List<PostDto> searchPosts(String keyword);
}
