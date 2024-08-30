package com.sb.application.solution.comment;

import jakarta.validation.constraints.NotBlank;

public record SolutionCommentRequest(
        @NotBlank String content,
        Long parentCommentId
) {
}
