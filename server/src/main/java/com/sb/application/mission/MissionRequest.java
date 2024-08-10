package com.sb.application.mission;

import com.sb.domain.mission.Language;
import com.sb.domain.mission.Mission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MissionRequest(
        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotNull(message = "언어는 필수입니다.")
        Language language,

        @NotBlank(message = "설명은 필수입니다.")
        String description,

        @NotBlank(message = "썸네일은 필수입니다.")
        String thumbnail,

        @NotBlank(message = "미션 주소는 필수입니다.")
        String url
) {

    public Mission toMission() {
        return new Mission(
                title,
                language,
                description,
                thumbnail,
                url
        );
    }
}
