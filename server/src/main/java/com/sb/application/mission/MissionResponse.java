package com.sb.application.mission;

public record MissionResponse(
        Long id,
        String title,
        String language,
        String description,
        String thumbnail,
        String url
) {
}
