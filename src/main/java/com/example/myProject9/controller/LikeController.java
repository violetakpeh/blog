package com.example.myProject9.controller;

import com.example.myProject9.service.LikeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like/{postId}/{userId}")
    public ResponseEntity<String> likeOrUnlikePost(@PathVariable Long postId, @PathVariable Long userId) {
        return likeService.likeOrUnlikePost(postId, userId);
    }

}
