package com.sb.application.solution.comment;


import jakarta.validation.constraints.NotBlank;

public record CommentRequest(
        Long parentCommentId,
        @NotBlank String content
) {
}
