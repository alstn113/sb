package com.sb.support.data;

import java.time.LocalDateTime;
import com.sb.domain.member.Member;
import com.sb.domain.mission.Mission;
import com.sb.domain.solution.Solution;

public class SolutionTestData {

    public static SolutionBuilder defaultSolution() {
        return new SolutionBuilder()
                .withMission(MissionTestData.defaultMission().build())
                .withMember(MemberTestData.defaultMember().build())
                .withTitle("title")
                .withDescription("description")
                .withUrl("https://url.com")
                .withSubmittedAt(null);
    }

    public static class SolutionBuilder {

        private Long id;
        private Mission mission;
        private Member member;
        private String title;
        private String description;
        private String url;
        private LocalDateTime submittedAt;

        public SolutionBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public SolutionBuilder withMission(Mission mission) {
            this.mission = mission;
            return this;
        }

        public SolutionBuilder withMember(Member member) {
            this.member = member;
            return this;
        }

        public SolutionBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public SolutionBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SolutionBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public SolutionBuilder withSubmittedAt(LocalDateTime submittedAt) {
            this.submittedAt = submittedAt;
            return this;
        }

        public Solution build() {
            return new Solution(id, mission, member, title, description, url, submittedAt);
        }
    }
}
