package com.sb.application.solution.comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.MissionRepository;
import com.sb.domain.solution.Solution;
import com.sb.domain.solution.SolutionRepository;
import com.sb.domain.solution.comment.SolutionComment;
import com.sb.domain.solution.comment.SolutionCommentRepository;
import com.sb.infra.exception.SbException;
import com.sb.support.IntegrationTestSupport;
import com.sb.support.data.MemberTestData;
import com.sb.support.data.MissionTestData;
import com.sb.support.data.SolutionCommentTestData;
import com.sb.support.data.SolutionTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SolutionCommentServiceTest extends IntegrationTestSupport {

    @Autowired
    private SolutionCommentService solutionCommentService;

    @Autowired
    private SolutionCommentRepository solutionCommentRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Test
    @DisplayName("댓글을 조회한다.")
    void getComment() {
        SolutionComment solutionComment = createSolutionComment();

        SolutionComment foundSolutionComment = solutionCommentService.getComment(solutionComment.getId());

        assertThat(foundSolutionComment).isEqualTo(solutionComment);
    }

    @Test
    @DisplayName("댓글 조회 시 존재하지 않는 경우 예외가 발생한다.")
    void getComment_notFound() {
        Long unknownId = -1L;

        assertThatThrownBy(() -> solutionCommentService.getComment(unknownId))
                .isInstanceOf(SbException.class)
                .hasMessage("존재하지 않는 댓글입니다.");
    }

    @Test
    @DisplayName("댓글 조회 시 삭제된 댓글일 경우 예외가 발생한다.")
    void getCommentFailedWhenDeleted() {
        Solution solution = createSolution();
        Member member = solution.getMember();
        SolutionComment deletedComment = SolutionCommentTestData.defaultSolutionComment()
                .withSolution(solution)
                .withMember(member)
                .withDeletedAt(LocalDateTime.now())
                .build();
        solutionCommentRepository.save(deletedComment);

        Long commentId = deletedComment.getId();
        assertThatThrownBy(() -> solutionCommentService.getComment(commentId))
                .isInstanceOf(SbException.class)
                .hasMessage("존재하지 않는 댓글입니다.");
    }

    @Test
    @DisplayName("댓글을 추가한다.")
    void addComment() {
        Solution solution = createSolution();
        Member member = solution.getMember();

        Long solutionId = solution.getId();
        Long memberId = member.getId();
        SolutionCommentRequest request = new SolutionCommentRequest(
                "댓글입니다.",
                null
        );
        CreateSolutionCommentResponse response = solutionCommentService.addComment(solutionId, request, memberId);

        assertAll(
                () -> assertThat(solutionCommentRepository.findAll()).hasSize(1),
                () -> assertThat(response.parentCommentId()).isNull()
        );
    }

    @Test
    @DisplayName("답글을 추가한다.")
    void addReply() {
        SolutionComment solutionComment = createSolutionComment();
        Member member = solutionComment.getMember();
        Solution solution = solutionComment.getSolution();

        Long solutionId = solution.getId();
        Long memberId = member.getId();
        SolutionCommentRequest request = new SolutionCommentRequest(
                "답글입니다.",
                solutionComment.getId()
        );
        CreateSolutionCommentResponse response = solutionCommentService.addComment(solutionId, request, memberId);

        assertAll(
                () -> assertThat(solutionCommentRepository.findAll()).hasSize(2),
                () -> assertThat(response.parentCommentId()).isEqualTo(solutionComment.getId())
        );
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteComment() {
        SolutionComment solutionComment = createSolutionComment();

        Long memberId = solutionComment.getMember().getId();
        Long commentId = solutionComment.getId();
        solutionCommentService.deleteComment(commentId, memberId);

        assertThat(solutionCommentRepository.findById(commentId))
                .map(SolutionComment::isDeleted)
                .hasValue(true);
    }

    @Test
    @DisplayName("댓글을 삭제 시 작성자가 아닌 경우 예외가 발생한다.")
    void deleteComment_notWrittenBy() {
        SolutionComment solutionComment = createSolutionComment();

        Long nonWriterId = -1L;
        Long commentId = solutionComment.getId();

        assertThatThrownBy(() -> solutionCommentService.deleteComment(commentId, nonWriterId))
                .isInstanceOf(SbException.class)
                .hasMessage("작성자만 댓글을 삭제할 수 있습니다.");
    }

    private Solution createSolution() {
        Mission mission = missionRepository.save(MissionTestData.defaultMission().build());
        Member member = memberRepository.save(MemberTestData.defaultMember().build());
        Solution solution = SolutionTestData.defaultSolution()
                .withMission(mission)
                .withMember(member)
                .build();

        return solutionRepository.save(solution);
    }

    private SolutionComment createSolutionComment() {
        Solution solution = createSolution();
        SolutionComment solutionComment = SolutionCommentTestData.defaultSolutionComment()
                .withSolution(solution)
                .withMember(solution.getMember())
                .build();
        solutionCommentRepository.save(solutionComment);

        return solutionComment;
    }
}
