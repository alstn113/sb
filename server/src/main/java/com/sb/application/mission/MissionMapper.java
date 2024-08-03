package com.sb.application.mission;

import java.util.List;
import com.sb.domain.mission.Mission;
import org.springframework.stereotype.Component;

@Component
public class MissionMapper {

    public MissionResponse toResponse(Mission mission) {
        return new MissionResponse(
                mission.getId(),
                mission.getTitle(),
                mission.getLanguage().name(),
                mission.getDescription(),
                mission.getThumbnail(),
                mission.getUrl()
        );
    }

    public List<MissionResponse> toResponses(List<Mission> missions) {
        return missions.stream()
                .map(this::toResponse)
                .toList();
    }

    public Mission toEntity(MissionRequest missionRequest) {
        return new Mission(
                missionRequest.title(),
                missionRequest.language(),
                missionRequest.description(),
                missionRequest.thumbnail(),
                missionRequest.url()
        );
    }
}
