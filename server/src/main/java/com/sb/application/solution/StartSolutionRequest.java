package com.sb.application.solution;

import jakarta.validation.constraints.NotNull;

public record StartSolutionRequest(
        @NotNull(message = "미션 식별자는 필수 값입니다.")
        Long missionId
) {
}
