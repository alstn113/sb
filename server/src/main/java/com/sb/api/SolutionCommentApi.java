package com.sb.api;

import java.net.URI;
import java.util.List;
import com.sb.api.auth.Auth;
import com.sb.application.auth.Accessor;
import com.sb.application.solution.comment.CommentRequest;
import com.sb.application.solution.comment.CommentService;
import com.sb.application.solution.comment.CommentsWithReplies;
import com.sb.application.solution.comment.RootComment;
import com.sb.domain.solution.comment.Comment;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RootComment>> getSolutionComments(@PathVariable Long solutionId) {
        CommentsWithReplies commentsWithReplies = commentService.getCommentsWithReplies(solutionId);

        return ResponseEntity.ok(commentsWithReplies.getValues());
    }

    @PostMapping("/solutions/{solutionId}/comments")
    public ResponseEntity<Comment> addSolutionComment(
            @PathVariable Long solutionId,
            @Valid @RequestBody CommentRequest request,
            @Auth Accessor accessor
    ) {
        Comment comment = commentService.addComment(solutionId, request, accessor.id());

        URI location = URI.create("/solutions/" + solutionId + "/comments/" + comment.getId());
        return ResponseEntity.created(location).body(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(
            @PathVariable Long commentId,
            @Auth Accessor accessor
    ) {
        commentService.deleteComment(commentId, accessor.id());
    }
}
