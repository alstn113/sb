package com.sb.application.mission;

import java.util.List;
import com.sb.application.auth.Accessor;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.MissionRepository;
import com.sb.domain.solution.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final SolutionRepository solutionRepository;

    public List<MissionResponse> getMissions() {
        List<Mission> missions = missionRepository.findAll();

        return missions.stream()
                .map(MissionResponse::from)
                .toList();
    }

    public MissionWithStatusResponse getMission(Accessor accessor, Long missionId) {
        Mission mission = missionRepository.getMissionById(missionId);

        if (accessor.isGuest()) {
            return MissionWithStatusResponse.from(mission, false);
        }

        boolean missionInProgress = solutionRepository
                .existsByMember_IdAndMission_IdAndSubmittedAtIsNull(accessor.id(), missionId);
        return MissionWithStatusResponse.from(mission, missionInProgress);
    }

    public MissionResponse createMission(MissionRequest missionRequest) {
        Mission mission = missionRequest.toMission();
        Mission savedMission = missionRepository.save(mission);

        return MissionResponse.from(savedMission);
    }

    public void deletePost(Long id) {
        missionRepository.deleteById(id);
    }
}
