package com.sb.application.solution.comment;

import java.time.LocalDateTime;
import java.util.List;
import com.sb.application.member.MemberResponse;
import com.sb.domain.solution.comment.Comment;

public record RootComment(
        Long id,
        Long solutionId,
        String content,
        MemberResponse member,
        List<Reply> replies,
        LocalDateTime createdAt
) {

    public static RootComment from(Comment comment, List<Reply> replies) {
        return new RootComment(
                comment.getId(),
                comment.getSolutionId(),
                comment.getContent(),
                MemberResponse.from(comment.getMember()),
                replies,
                comment.getCreatedAt()
        );
    }
}
