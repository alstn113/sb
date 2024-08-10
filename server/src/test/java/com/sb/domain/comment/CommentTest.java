package com.sb.domain.comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.sb.domain.member.Member;
import com.sb.domain.mission.Mission;
import com.sb.domain.solution.comment.Comment;
import com.sb.domain.solution.Solution;
import com.sb.support.data.MemberTestData;
import com.sb.support.data.MissionTestData;
import com.sb.support.data.SolutionTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    @Test
    @DisplayName("댓글을 생성한다.")
    void create() {
        Member member = MemberTestData.defaultMember().build();
        Mission mission = MissionTestData.defaultMission().build();
        Solution solution = SolutionTestData.defaultSolution()
                .withMission(mission)
                .withMember(member)
                .build();

        assertThatCode(() -> new Comment("content", solution, member))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void delete() {
        Member member = MemberTestData.defaultMember().build();
        Mission mission = MissionTestData.defaultMission().build();
        Solution solution = SolutionTestData.defaultSolution()
                .withMission(mission)
                .withMember(member)
                .build();
        Comment comment = new Comment("content", solution, member);

        comment.delete();

        assertThat(comment.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("대댓글을 작성한다.")
    void createReply() {
        // given
        Member member = MemberTestData.defaultMember().build();
        Mission mission = MissionTestData.defaultMission().build();
        Solution solution = SolutionTestData.defaultSolution()
                .withMission(mission)
                .withMember(member)
                .build();
        Comment rootComment = new Comment("content", solution, member);

        // when
        Comment reply = Comment.reply("reply content", solution, member, rootComment);

        // then
        assertThat(reply.getParentComment()).isEqualTo(rootComment);
    }

    @Test
    @DisplayName("대대댓글을 작성할 경우 댓글로 작성한다.")
    void createReplyToReply() {
        // given
        Member member = MemberTestData.defaultMember().build();
        Mission mission = MissionTestData.defaultMission().build();
        Solution solution = SolutionTestData.defaultSolution()
                .withMission(mission)
                .withMember(member)
                .build();
        Comment rootComment = new Comment("content", solution, member);
        Comment reply = Comment.reply("reply content", solution, member, rootComment);

        // when
        Comment replyToReply = Comment.reply("reply to reply content", solution, member, reply);

        // then
        assertAll(
                () -> assertThat(reply.getParentComment()).isEqualTo(rootComment),
                () -> assertThat(replyToReply.getParentComment()).isEqualTo(rootComment)
        );
    }
}
