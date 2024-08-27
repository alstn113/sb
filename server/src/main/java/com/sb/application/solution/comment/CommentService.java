package com.sb.application.solution.comment;

import java.util.List;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.solution.Solution;
import com.sb.domain.solution.SolutionRepository;
import com.sb.domain.solution.comment.Comment;
import com.sb.domain.solution.comment.CommentRepository;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final SolutionRepository solutionRepository;
    private final CommentRepository commentRepository;

    public Comment getComment(Long commentId) {
        Comment comment = commentRepository.getCommentById(commentId);
        if (comment.isDeleted()) {
            throw new SbException(ExceptionType.COMMENT_NOT_FOUND);
        }

        return comment;
    }

    public CommentsWithReplies getCommentsWithReplies(Long solutionId) {
        Solution solution = solutionRepository.getSolutionById(solutionId);
        List<Comment> comments = commentRepository.findAllBySolution_IdOrderByCreatedAtAsc(solution.getId());

        return CommentsWithReplies.from(comments);
    }

    public CommentResponse addComment(Long solutionId, CommentRequest request, Long memberId) {
        Member member = memberRepository.getMemberById(memberId);
        Solution solution = solutionRepository.getSolutionById(solutionId);

        if (request.parentCommentId() == null) {
            return addRootComment(request, solution, member);
        }

        return addReply(request, solution, member);
    }

    private CommentResponse addRootComment(CommentRequest request, Solution solution, Member member) {
        Comment comment = new Comment(request.content(), solution, member);
        Comment savedComment = commentRepository.save(comment);

        return CommentResponse.rootComment(savedComment);
    }

    private CommentResponse addReply(CommentRequest request, Solution solution, Member member) {
        Comment parentComment = getComment(request.parentCommentId());
        Comment reply = Comment.reply(request.content(), solution, member, parentComment);
        Comment savedReply = commentRepository.save(reply);

        return CommentResponse.reply(savedReply);
    }

    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = getComment(commentId);

        if (comment.isNotOwnedBy(memberId)) {
            throw new SbException(ExceptionType.COMMENT_NOT_YOURS);
        }

        comment.delete();
    }
}
