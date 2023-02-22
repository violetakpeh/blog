package com.example.myProject9.controller;

import com.example.myProject9.dto.CommentDto;
import com.example.myProject9.model.Comment;
import com.example.myProject9.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<Comment> makeComment(@PathVariable Long postId, @PathVariable Long userId, @RequestBody CommentDto commentDto) {
         Comment comment = commentService.commentOnPost(postId, userId, commentDto);
         return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId, @PathVariable Long postId) {
        return commentService.deleteComment(commentId, postId);
    }

}
