package com.sb.application.solution.comment;

import java.time.LocalDateTime;
import com.sb.application.member.MemberResponse;
import com.sb.domain.solution.comment.SolutionComment;

public record SolutionReplyResponse(
        Long id,
        Long solutionId,
        Long parentCommentId,
        String content,
        MemberResponse member,
        LocalDateTime createdAt
) {

    public static SolutionReplyResponse from(SolutionComment reply) {
        return new SolutionReplyResponse(
                reply.getId(),
                reply.getSolutionId(),
                reply.getParentCommentId(),
                reply.getContent(),
                MemberResponse.from(reply.getMember()),
                reply.getCreatedAt()
        );
    }
}
