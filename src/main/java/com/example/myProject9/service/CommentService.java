package com.example.myProject9.service;

import com.example.myProject9.dto.CommentDto;
import com.example.myProject9.model.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Comment commentOnPost(Long postId, Long userId, CommentDto commentDto);

    ResponseEntity<Comment> deleteComment(Long commentId, Long postId);
}
