package com.backend.bloggapp.services.impl;

import com.backend.bloggapp.entities.Category;
import com.backend.bloggapp.entities.Post;
import com.backend.bloggapp.entities.User;
import com.backend.bloggapp.exceptions.ResourceNotFoundException;
import com.backend.bloggapp.payloads.PostDto;
import com.backend.bloggapp.repositories.CategoryRepository;
import com.backend.bloggapp.repositories.PostRepository;
import com.backend.bloggapp.repositories.UserRepository;
import com.backend.bloggapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryID", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        return this.modelMapper.map(this.postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        return null;
    }

    @Override
    public void deletePost(Long postId) {

    }

    @Override
    public PostDto getPostById(Long postId) {
        return this.modelMapper.map(this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostID", postId)), PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> allPosts = this.postRepository.findAll();
        return allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryID", categoryId));
        List<Post> allPosts = this.postRepository.findByCategory(category);
        return allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));
        List<Post> allPosts = this.postRepository.findByUser(user);
        return allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
