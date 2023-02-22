package com.example.myProject9.repository;

import com.example.myProject9.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findLikeByPostIdAndUserId(Long postId, Long userId);
}
