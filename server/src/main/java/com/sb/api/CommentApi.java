package com.sb.api;

import com.sb.application.solution.CommentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentApi {

    private final CommentService commentService;

    public CommentApi(CommentService commentService) {
        this.commentService = commentService;
    }
}
