package com.sb.application.solution.comment;

import java.time.LocalDateTime;
import com.sb.application.member.MemberResponse;
import com.sb.domain.solution.comment.SolutionComment;


public record CreateSolutionCommentResponse(
        Long id,
        Long solutionId,
        Long parentCommentId,
        String content,
        MemberResponse member,
        LocalDateTime createdAt
) {

    public static CreateSolutionCommentResponse from(SolutionComment comment) {
        return new CreateSolutionCommentResponse(
                comment.getId(),
                comment.getSolutionId(),
                comment.getParentCommentId(),
                comment.getContent(),
                MemberResponse.from(comment.getMember()),
                comment.getCreatedAt()
        );
    }
}
