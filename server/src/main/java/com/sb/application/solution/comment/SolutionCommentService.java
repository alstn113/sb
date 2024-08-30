package com.sb.application.solution.comment;

import java.util.List;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.solution.Solution;
import com.sb.domain.solution.SolutionRepository;
import com.sb.domain.solution.comment.SolutionComment;
import com.sb.domain.solution.comment.SolutionCommentRepository;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SolutionCommentService {

    private final CommentGroupingService commentGroupingService;
    private final SolutionCommentRepository solutionCommentRepository;
    private final MemberRepository memberRepository;
    private final SolutionRepository solutionRepository;


    public SolutionComment getComment(Long commentId) {
        SolutionComment comment = solutionCommentRepository.getCommentById(commentId);

        if (comment.isDeleted()) {
            throw new SbException(ExceptionType.COMMENT_NOT_FOUND);
        }

        return comment;
    }

    @Transactional(readOnly = true)
    public List<SolutionCommentRepliesResponse> getCommentsWithReplies(Long solutionId) {
        List<SolutionComment> comments = solutionCommentRepository.findAllBySolution_IdOrderByCreatedAtAsc(solutionId);

        return commentGroupingService.groupReplies(comments);
    }

    @Transactional
    public CreateSolutionCommentResponse addComment(Long solutionId, SolutionCommentRequest request, Long memberId) {
        Member member = memberRepository.getMemberById(memberId);
        Solution solution = solutionRepository.getSolutionById(solutionId);

        boolean isReply = request.parentCommentId() != null;
        if (isReply) {
            SolutionComment reply = createReply(request, member);
            return CreateSolutionCommentResponse.from(reply);
        }

        SolutionComment rootComment = createRootComment(request, solution, member);
        return CreateSolutionCommentResponse.from(rootComment);
    }

    private SolutionComment createReply(SolutionCommentRequest request, Member member) {
        SolutionComment parentComment = getComment(request.parentCommentId());
        SolutionComment reply = parentComment.reply(request.content(), member);

        return solutionCommentRepository.save(reply);
    }

    private SolutionComment createRootComment(SolutionCommentRequest request, Solution solution, Member member) {
        SolutionComment rootComment = SolutionComment.create(request.content(), solution, member);

        return solutionCommentRepository.save(rootComment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long memberId) {
        SolutionComment comment = getComment(commentId);

        if (comment.isNotWrittenBy(memberId)) {
            throw new SbException(ExceptionType.COMMENT_NOT_WRITTEN_BY_MEMBER);
        }

        comment.delete();
    }
}
