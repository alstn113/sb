package com.sb.application.mission;

import com.sb.domain.mission.Mission;

public record MissionResponse(
        Long id,
        String title,
        String language,
        String description,
        String thumbnail,
        String url
) {

    public static MissionResponse from(Mission mission) {
        return new MissionResponse(
                mission.getId(),
                mission.getTitle(),
                mission.getLanguage().name(),
                mission.getDescription(),
                mission.getThumbnail(),
                mission.getUrl()
        );
    }
}
