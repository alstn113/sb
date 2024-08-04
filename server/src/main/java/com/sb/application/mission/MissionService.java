package com.sb.application.mission;

import java.util.List;
import com.sb.application.auth.Accessor;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.MissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;

    public MissionService(MissionRepository missionRepository, MissionMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.missionMapper = missionMapper;
    }

    @Transactional(readOnly = true)
    public List<MissionResponse> getMissions() {
        List<Mission> missions = missionRepository.findAll();

        return missionMapper.toResponses(missions);
    }

    @Transactional(readOnly = true)
    public MissionResponse getMission(Accessor accessor, Long missionId) {
        Mission mission = missionRepository.getMissionById(missionId);

        if (accessor.isGuest()) {
            return missionMapper.toResponse(mission);
        }

        return missionMapper.toResponse(mission);

    }

    @Transactional
    public MissionResponse createMission(MissionRequest missionRequest) {
        Mission mission = missionMapper.toEntity(missionRequest);
        Mission savedMission = missionRepository.save(mission);

        return missionMapper.toResponse(savedMission);
    }

    @Transactional
    public void deletePost(Long id) {
        missionRepository.deleteById(id);
    }
}
