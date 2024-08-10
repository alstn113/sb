package com.sb.api;

import com.sb.api.auth.Auth;
import com.sb.application.auth.Accessor;
import com.sb.application.solution.comment.CommentRequest;
import com.sb.application.solution.comment.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionCommentApi {

    private final CommentService commentService;

    public SolutionCommentApi(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/solutions/{solutionId}/comments")
    public void getSolutionComments(@PathVariable Long solutionId) {
    }

    @PostMapping("/solutions/{solutionId}/comments")
    public void addSolutionComment(
            @PathVariable Long solutionId,
            @Valid @RequestBody CommentRequest request,
            @Auth Accessor accessor
    ) {
    }

    @DeleteMapping("/solutions/{solutionId}/comments/{commentId}")
    public void deleteSolutionComment(
            @PathVariable Long solutionId,
            @PathVariable Long commentId,
            @Auth Accessor accessor
    ) {
    }
}
