package com.sb.application.solution;

import java.time.LocalDateTime;
import com.sb.application.member.MemberResponse;
import com.sb.application.mission.MissionResponse;

public record SolutionResponse(
        Long id,
        MissionResponse mission,
        MemberResponse member,
        String title,
        String description,
        String url,
        LocalDateTime startedAt,
        LocalDateTime submittedAt
) {
}
