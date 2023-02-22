package com.example.myProject9.service.implementation;

import com.example.myProject9.dto.CommentDto;
import com.example.myProject9.exception.CommentNotFoundException;
import com.example.myProject9.exception.PostNotFoundException;
import com.example.myProject9.exception.UserNotFoundException;
import com.example.myProject9.model.Comment;
import com.example.myProject9.model.Post;
import com.example.myProject9.model.User;
import com.example.myProject9.repository.CommentRepository;
import com.example.myProject9.repository.PostRepository;
import com.example.myProject9.repository.UserRepository;
import com.example.myProject9.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Component
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    @Override
    public Comment commentOnPost(Long postId, Long userId, CommentDto commentDto) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("user not found"));
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("post not found"));
        Comment newComment = new Comment();
        newComment.setBody(commentDto.getBody());
        newComment.setCreatedAt(LocalDateTime.now());
        //newComment.setMadeBy(user.getName());
        //newComment.setUserId(userId);
        //newComment.setPostId(postId);
        post.setCommentsCount(post.getCommentsCount() + 1L);
        commentRepo.save(newComment);

        return newComment;
    }

    @Override
    public ResponseEntity<Comment> deleteComment(Long commentId, Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("post not found"));
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException("comment not found"));
        post.setCommentsCount(post.getCommentsCount() - 1);
        commentRepo.delete(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
