package com.sb.application.solution.comment;

import com.sb.domain.solution.comment.Comment;

public record Reply(Long id, String content) {

    public static Reply from(Comment comment) {
        return new Reply(comment.getId(), comment.getContent());
    }
}
