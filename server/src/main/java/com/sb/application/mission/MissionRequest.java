package com.sb.application.mission;

import jakarta.validation.constraints.NotBlank;

public record MissionRequest(
        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotBlank(message = "설명은 필수입니다.")
        String description
) {
}
