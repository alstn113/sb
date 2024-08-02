package com.sb.application.mission;

import java.util.List;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.MissionRepository;
import org.springframework.stereotype.Service;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;

    public MissionService(MissionRepository missionRepository, MissionMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.missionMapper = missionMapper;
    }

    public List<MissionResponse> getMissions() {
        List<Mission> missions = missionRepository.findAll();

        return missionMapper.toResponses(missions);
    }

    public MissionResponse getMission(Long id) {
        Mission mission = missionRepository.getById(id);

        return missionMapper.toResponse(mission);

    }

    public MissionResponse createMission(MissionRequest missionRequest) {
        Mission mission = missionMapper.toEntity(missionRequest);
        Mission savedMission = missionRepository.save(mission);

        return missionMapper.toResponse(savedMission);
    }

    public void deletePost(Long id) {
        missionRepository.deleteById(id);
    }
}
