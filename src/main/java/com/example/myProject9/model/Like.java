package com.example.myProject9.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;
    private Long userId;
    private String likedBy;
    private Long postId;
    private LocalDateTime likedAt;
}
