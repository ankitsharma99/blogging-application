package com.backend.bloggapp.services.impl;

import com.backend.bloggapp.entities.Category;
import com.backend.bloggapp.entities.Post;
import com.backend.bloggapp.entities.User;
import com.backend.bloggapp.exceptions.ResourceNotFoundException;
import com.backend.bloggapp.payloads.PostDto;
import com.backend.bloggapp.payloads.PostResponse;
import com.backend.bloggapp.repositories.CategoryRepository;
import com.backend.bloggapp.repositories.PostRepository;
import com.backend.bloggapp.repositories.UserRepository;
import com.backend.bloggapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostID", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        return this.modelMapper.map(this.postRepository.save(post), PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostID", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Long postId) {
        return this.modelMapper.map(this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostID", postId)), PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Pageable p = null;
        if(sortDir.equals("asc")) {
            p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        }
        else {
            p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }

        Page<Post> pagePost = this.postRepository.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
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
        List<Post> searchedPosts = this.postRepository.findByPostTitleContaining(keyword);
        return searchedPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAllPosts() {
        this.postRepository.deleteAll();
    }
}
