package com.example.myProject9.controller;

import com.example.myProject9.dto.PostDto;
import com.example.myProject9.model.Comment;
import com.example.myProject9.model.Post;
import com.example.myProject9.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> viewAllPosts() {
        return postService.getAllPosts();
    }
    @PostMapping("/add/{categoryName}")
    public ResponseEntity<?> addNewPost(@RequestBody PostDto postDto, @PathVariable String categoryName) {
        Post post = postService.createPost(postDto, categoryName);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> viewPostById(@PathVariable Long id) {
        Post post = postService.viewPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @GetMapping("/{postId}/comments")
    public List<Comment> viewAllPostComments(@PathVariable Long postId) {
        return postService.viewAllPostComments(postId);
    }
    @GetMapping("/search/{searchWord}")
    public List<PostDto> searchForPost(@PathVariable String searchWord) {
        return postService.searchPost(searchWord);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editPost(@PathVariable Long id, @RequestBody PostDto postDto) {
        Post updatedPost = postService.editPost(id, postDto);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
