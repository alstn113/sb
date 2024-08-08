package com.sb.application.solution;

import com.sb.domain.solution.Comment;

public record Reply(Long id, String content) {

    public static Reply from(Comment comment) {
        return new Reply(comment.getId(), comment.getContent());
    }
}
