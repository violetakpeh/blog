package com.example.myProject9.service;

import org.springframework.http.ResponseEntity;

public interface LikeService {
   ResponseEntity<String> likeOrUnlikePost(Long postId, Long userId);
}
