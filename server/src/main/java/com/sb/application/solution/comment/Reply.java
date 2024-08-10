package com.sb.application.solution.comment;

import java.time.LocalDateTime;
import com.sb.application.member.MemberResponse;
import com.sb.domain.solution.comment.Comment;

public record Reply(
        Long id,
        Long parentCommentId,
        String content,
        MemberResponse member,
        LocalDateTime createdAt
) {

    public static Reply from(Comment comment) {
        return new Reply(
                comment.getId(),
                comment.getParentCommentId(),
                comment.getContent(),
                MemberResponse.from(comment.getMember()),
                comment.getCreatedAt()
        );
    }
}
