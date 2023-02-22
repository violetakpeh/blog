package com.example.myProject9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommentDto {
    private String body;
    private String madeBy;
}
