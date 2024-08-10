package com.sb.application.solution;

public record CommentRequest(
        Long parentCommentId,
        String content
) {
}
