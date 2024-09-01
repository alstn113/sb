package com.sb.api;

import java.net.URI;
import java.util.List;
import com.sb.api.auth.Auth;
import com.sb.application.auth.Accessor;
import com.sb.application.solution.comment.CreateSolutionCommentResponse;
import com.sb.application.solution.comment.SolutionCommentRepliesResponse;
import com.sb.application.solution.comment.SolutionCommentRequest;
import com.sb.application.solution.comment.SolutionCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "솔루션 댓글 API")
public class SolutionCommentApi {

    private final SolutionCommentService solutionCommentService;

    @GetMapping("/api/v1/solutions/{solutionId}/comments")
    @Operation(summary = "솔루션 댓글 조회 API", description = "솔루션의 댓글 목록을 조회합니다. 댓글들과 댓글들에 대한 답글을 조회합니다.")
    public ResponseEntity<List<SolutionCommentRepliesResponse>> getComments(
            @PathVariable Long solutionId
    ) {
        List<SolutionCommentRepliesResponse> responses = solutionCommentService.getCommentsWithReplies(solutionId);

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/api/v1/solutions/{solutionId}/comments")
    @Operation(summary = "솔루션 댓글 추가 API", description = "솔루션에 댓글을 추가합니다. 부모 댓글 식별자로 답글을 추가할 수 있습니다.")
    public ResponseEntity<CreateSolutionCommentResponse> addComment(
            @PathVariable Long solutionId,
            @Valid @RequestBody SolutionCommentRequest request,
            @Auth Accessor accessor
    ) {
        CreateSolutionCommentResponse response = solutionCommentService.addComment(solutionId, request, accessor.id());

        URI location = URI.create("/api/v1/solutions/" + response.solutionId() + "/comments/" + response.id());

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/api/v1/solutions/comments/{commentId}")
    @Operation(summary = "솔루션 댓글 삭제 API", description = "솔루션의 댓글을 삭제합니다.")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @Auth Accessor accessor
    ) {
        solutionCommentService.deleteComment(commentId, accessor.id());

        return ResponseEntity.noContent().build();
    }
}
