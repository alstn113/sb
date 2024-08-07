package com.sb.application.mission;

import java.util.List;
import com.sb.application.auth.Accessor;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.MissionRepository;
import com.sb.domain.solution.SolutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final SolutionRepository solutionRepository;
    private final MissionMapper missionMapper;

    public MissionService(
            MissionRepository missionRepository,
            SolutionRepository solutionRepository,
            MissionMapper missionMapper
    ) {
        this.missionRepository = missionRepository;
        this.solutionRepository = solutionRepository;
        this.missionMapper = missionMapper;
    }

    @Transactional(readOnly = true)
    public List<MissionResponse> getMissions() {
        List<Mission> missions = missionRepository.findAll();

        return missionMapper.toResponses(missions);
    }

    @Transactional(readOnly = true)
    public MissionWithStatusResponse getMission(Accessor accessor, Long missionId) {
        Mission mission = missionRepository.getMissionById(missionId);

        if (accessor.isGuest()) {
            return missionMapper.toStatusResponse(mission, false);
        }

        boolean missionInProgress = solutionRepository
                .existsByMember_IdAndMission_IdAndSubmittedAtIsNull(accessor.id(), missionId);
        return missionMapper.toStatusResponse(mission, missionInProgress);
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
