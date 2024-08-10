package com.sb.application.solution.comment;

import java.util.List;
import com.sb.domain.solution.comment.Comment;
import com.sb.domain.solution.comment.CommentRepository;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getComment(Long commentId) {
        Comment comment = commentRepository.getCommentById(commentId);
        if (comment.isDeleted()) {
            throw new SbException(ExceptionType.COMMENT_NOT_FOUND);
        }

        return comment;
    }

    public CommentsWithReplies getComments(Long solutionId) {
        List<Comment> comments = commentRepository.findAllBySolution_Id(solutionId);

        return CommentsWithReplies.from(comments);
    }

    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = getComment(commentId);

        if (comment.isNotOwnedBy(memberId)) {
            throw new SbException(ExceptionType.COMMENT_NOT_YOURS);
        }

        comment.delete();
    }
}
