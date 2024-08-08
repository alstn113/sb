package com.sb.support.data;

import java.time.LocalDate;
import com.sb.domain.solution.Comment;
import com.sb.domain.member.Member;
import com.sb.domain.solution.Solution;

public class CommentTestData {

    public static CommentBuilder defaultComment() {
        return new CommentBuilder()
                .withContent("content")
                .withSolution(SolutionTestData.defaultSolution().build())
                .withMember(MemberTestData.defaultMember().build())
                .withParent(null)
                .withDeletedAt(null);
    }

    public static class CommentBuilder {

        private Long id;
        private String content;
        private Solution solution;
        private Member member;
        private Comment parent;
        private LocalDate deletedAt;

        public CommentBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CommentBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public CommentBuilder withSolution(Solution solution) {
            this.solution = solution;
            return this;
        }

        public CommentBuilder withMember(Member member) {
            this.member = member;
            return this;
        }

        public CommentBuilder withParent(Comment parent) {
            this.parent = parent;
            return this;
        }

        public CommentBuilder withDeletedAt(LocalDate deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Comment build() {
            return new Comment(id, content, solution, member, parent, deletedAt);
        }
    }
}
