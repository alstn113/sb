package com.sb.application.solution.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sb.domain.solution.comment.Comment;

public class CommentsWithReplies {

    private final List<RootComment> values;

    private CommentsWithReplies(List<RootComment> commentsWithReplies) {
        this.values = commentsWithReplies;
    }

    public static CommentsWithReplies from(List<Comment> comments) {
        List<Comment> rootComments = comments.stream()
                .filter(Comment::isRoot)
                .toList();

        Map<Long, List<Reply>> repliesMap = mapRepliesToRootComments(comments);
        List<RootComment> commentsWithReplies = attachRepliesToRootComments(rootComments, repliesMap);

        return new CommentsWithReplies(commentsWithReplies);
    }

    private static Map<Long, List<Reply>> mapRepliesToRootComments(List<Comment> comments) {
        Map<Long, List<Reply>> repliesMap = new HashMap<>();
        comments.stream()
                .filter(it -> !it.isRoot())
                .filter(it -> !it.isDeleted())
                .forEach(it -> {
                    Long parentCommentId = it.getParentCommentId();
                    repliesMap.computeIfAbsent(parentCommentId, k -> new ArrayList<>())
                            .add(Reply.from(it));
                });

        return repliesMap;
    }

    private static List<RootComment> attachRepliesToRootComments(
            List<Comment> rootComments,
            Map<Long, List<Reply>> repliesMap
    ) {
        return rootComments.stream()
                .map(it -> {
                    List<Reply> replies = repliesMap.getOrDefault(it.getId(), List.of());
                    return RootComment.from(it, replies);
                })
                .toList();
    }

    public List<RootComment> getValues() {
        return values;
    }
}
