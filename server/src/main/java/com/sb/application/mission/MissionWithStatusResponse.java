package com.sb.application.mission;

public record MissionWithStatusResponse(
        Long id,
        String title,
        String language,
        String description,
        String thumbnail,
        String url,
        boolean inProgress
) {
}
