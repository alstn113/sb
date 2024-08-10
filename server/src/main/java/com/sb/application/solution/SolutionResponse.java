package com.sb.application.solution;

import java.time.LocalDateTime;
import com.sb.application.member.MemberResponse;
import com.sb.application.mission.MissionResponse;
import com.sb.domain.solution.Solution;

public record SolutionResponse(
        Long id,
        MissionResponse mission,
        MemberResponse member,
        String title,
        String description,
        String url,
        LocalDateTime submittedAt
) {

    public static SolutionResponse from(Solution solution) {
        return new SolutionResponse(
                solution.getId(),
                MissionResponse.from(solution.getMission()),
                MemberResponse.from(solution.getMember()),
                solution.getTitle(),
                solution.getDescription(),
                solution.getUrl(),
                solution.getSubmittedAt()
        );
    }
}
