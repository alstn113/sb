package com.sb.application.solution;

import java.util.List;
import com.sb.domain.solution.Comment;

public record RootComment(
        Long id,
        String content,
        List<Reply> replies
) {

    public static RootComment from(Comment comment, List<Reply> replies) {
        return new RootComment(comment.getId(), comment.getContent(), replies);
    }
}
