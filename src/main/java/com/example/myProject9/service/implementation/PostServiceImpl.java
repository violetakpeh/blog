package com.example.myProject9.service.implementation;

import com.example.myProject9.dto.PostDto;
import com.example.myProject9.exception.CategoryNotFoundException;
import com.example.myProject9.exception.PostNotFoundException;
import com.example.myProject9.model.Category;
import com.example.myProject9.model.Comment;
import com.example.myProject9.model.Post;
import com.example.myProject9.repository.CategoryRepository;
import com.example.myProject9.repository.CommentRepository;
import com.example.myProject9.repository.PostRepository;
import com.example.myProject9.service.CategoryService;
import com.example.myProject9.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CategoryService categoryService;
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();

    }


    @Override
    public Post createPost(PostDto postDto, String categoryName) {
       Category category = categoryService.findCategoryByName(categoryName);
       // if (category == null)
         //   throw new CategoryNotFoundException("category does not exist, create new category for this post");
            Post post = new Post();
            post.setTitle(postDto.getTitle());
            post.setBody(postDto.getBody());
            post.setCategory(category);
            post.setCreatedAt(LocalDateTime.now());
            post.setCommentsCount(0L);
            post.setLikesCount(0L);
             postRepository.save(post);

        return post;

    }
//    private Post getPostResponseEntity(PostDto postDto) {
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setBody(postDto.getBody());
//        post.setCommentsCount(0L);
//        post.setLikesCount(0L);
//        return post;
//    }

    @Override
    public Post viewPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    @Override
    public List<Comment> viewAllPostComments(Long postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    @Override
    public List<PostDto> searchPost(String searchWord) {
        searchWord = searchWord.toLowerCase();
        List<Post> posts = postRepository.findAll();
        List<PostDto> result = new ArrayList<>();
        for(Post post : posts){
            String title = post.getTitle().toLowerCase();
            String body = post.getBody().toLowerCase();
            if(title.contains(searchWord) || body.contains(searchWord)){
                getPostDto(result, post);
            }
        }
        return result;
    }

    @Override
    public Post editPost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
        return post;
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
        postRepository.delete(post);
    }

    private void getPostDto(List<PostDto> result, Post post) {
        PostDto postDto = new PostDto();
        postDto.setCategory(post.getCategory().getCategory_name());
        postDto.setBody(post.getBody());
        postDto.setTitle(post.getTitle());
        postDto.setImage(post.getImage());
        postDto.setLikesCount(post.getLikesCount());
        postDto.setCommentsCount(post.getCommentsCount());

        result.add(postDto);
    }
}
