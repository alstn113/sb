package com.sb.application.solution.comment;

import java.time.LocalDateTime;
import com.sb.application.member.MemberResponse;
import com.sb.domain.solution.comment.Comment;

public record CommentResponse(
        Long id,
        Long solutionId,
        Long parentCommentId,
        String content,
        MemberResponse member,
        LocalDateTime createdAt,
        boolean isDeleted
) {

    public static CommentResponse rootComment(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getSolutionId(),
                null,
                comment.getContent(),
                MemberResponse.from(comment.getMember()),
                comment.getCreatedAt(),
                comment.isDeleted()
        );
    }

    public static CommentResponse reply(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getSolutionId(),
                comment.getParentCommentId(),
                comment.getContent(),
                MemberResponse.from(comment.getMember()),
                comment.getCreatedAt(),
                comment.isDeleted()
        );
    }
}
