package com.sb.ui;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sb.application.PostRequest;
import com.sb.application.PostResponse;
import com.sb.application.PostService;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponse getPost(@PathVariable long id) {
        return postService.getPost(id);
    }

    @PostMapping("/posts")
    public PostResponse createPost(PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }
}
