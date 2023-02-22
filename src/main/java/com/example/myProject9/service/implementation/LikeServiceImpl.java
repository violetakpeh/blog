package com.example.myProject9.service.implementation;

import com.example.myProject9.exception.PostNotFoundException;
import com.example.myProject9.exception.UserNotFoundException;
import com.example.myProject9.model.Like;
import com.example.myProject9.model.Post;
import com.example.myProject9.model.User;
import com.example.myProject9.repository.LikeRepository;
import com.example.myProject9.repository.PostRepository;
import com.example.myProject9.repository.UserRepository;
import com.example.myProject9.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Component
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final UserRepository userRepo;
    private final PostRepository postRepo;
    private final LikeRepository likeRepo;
    @Override
    public ResponseEntity<String> likeOrUnlikePost(Long postId, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("user with id " + userId + " not found" ));
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("post with id " + postId + " not found"));
        Like like = likeRepo.findLikeByPostIdAndUserId(postId, userId);
        if (like != null) {
            likeRepo.delete(like);
            post.setLikesCount(post.getLikesCount() - 1L);

            return new ResponseEntity<>("post unliked", HttpStatus.ACCEPTED);
        }
        Like newLike = new Like();
        newLike.setPostId(postId);
        newLike.setUserId(userId);
        newLike.setLikedBy(user.getName());
        newLike.setLikedAt(LocalDateTime.now());

        post.setLikesCount(post.getLikesCount() + 1L);

        likeRepo.save(newLike);

        return new ResponseEntity<>("Like successful", HttpStatus.ACCEPTED);
    }


}
