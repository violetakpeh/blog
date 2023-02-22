package com.example.myProject9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private String title;
    private String body;
    private String image;
    private String category;
    private Long commentsCount;
    private Long likesCount;
}
