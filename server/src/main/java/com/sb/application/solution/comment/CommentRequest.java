package com.sb.application.solution.comment;


import jakarta.validation.constraints.NotBlank;

public record CommentRequest(
        Long parentCommentId,

        @NotBlank(message = "내용은 필수입니다.")
        String content
) {
}
