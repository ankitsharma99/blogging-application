package com.backend.bloggapp.services.impl;

import com.backend.bloggapp.entities.Comment;
import com.backend.bloggapp.entities.Post;
import com.backend.bloggapp.exceptions.ResourceNotFoundException;
import com.backend.bloggapp.payloads.CommentDto;
import com.backend.bloggapp.repositories.CommentRepository;
import com.backend.bloggapp.repositories.PostRepository;
import com.backend.bloggapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);

        return this.modelMapper.map(this.commentRepository.save(comment), CommentDto.class);


    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentID", commentId));
        this.commentRepository.delete(comment);
    }
}
