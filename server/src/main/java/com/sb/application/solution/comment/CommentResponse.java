package com.sb.application.solution.comment;

import java.time.LocalDateTime;
import com.sb.application.member.MemberResponse;
import com.sb.domain.solution.comment.Comment;

public record CommentResponse(
        Long id,
        Long parentCommentId,
        String content,
        MemberResponse member,
        LocalDateTime createdAt
) {

    public static CommentResponse rootComment(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                null,
                comment.getContent(),
                MemberResponse.from(comment.getMember()),
                comment.getCreatedAt()
        );
    }

    public static CommentResponse reply(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getParentCommentId(),
                comment.getContent(),
                MemberResponse.from(comment.getMember()),
                comment.getCreatedAt()
        );
    }
}
