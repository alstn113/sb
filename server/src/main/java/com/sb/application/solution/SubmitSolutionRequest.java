package com.sb.application.solution;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubmitSolutionRequest(
        @NotNull(message = "솔루션 식별자는 필수 값입니다.")
        Long solutionId,

        @NotBlank(message = "제목은 필수 값입니다.")
        String title,

        @NotBlank(message = "설명은 필수 값입니다.")
        String description,

        @NotBlank(message = "제출 주소는 필수 값입니다.")
        String url
) {
}
