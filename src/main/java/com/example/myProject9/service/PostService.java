package com.example.myProject9.service;

import com.example.myProject9.dto.PostDto;
import com.example.myProject9.model.Comment;
import com.example.myProject9.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post createPost(PostDto postDto, String CategoryName);

    Post viewPostById(Long id);

    List<Comment> viewAllPostComments(Long postId);

    List<PostDto> searchPost(String searchWord);

    Post editPost(Long id, PostDto postDto);

    void deletePost(Long id);
}
