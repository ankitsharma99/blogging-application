package com.backend.bloggapp.services;

import com.backend.bloggapp.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId);
    void deleteComment(Long commentId);
}
