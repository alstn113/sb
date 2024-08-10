package com.sb.application.mission;

import com.sb.domain.mission.Mission;

public record MissionWithStatusResponse(
        Long id,
        String title,
        String language,
        String description,
        String thumbnail,
        String url,
        boolean inProgress
) {

    public static MissionWithStatusResponse from(Mission mission, boolean inProgress) {
        return new MissionWithStatusResponse(
                mission.getId(),
                mission.getTitle(),
                mission.getLanguage().name(),
                mission.getDescription(),
                mission.getThumbnail(),
                mission.getUrl(),
                inProgress
        );
    }
}
