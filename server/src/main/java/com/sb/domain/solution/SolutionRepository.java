package com.sb.domain.solution;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    Optional<Solution> findByMember_IdAndMission_IdAndSubmittedAtIsNull(Long memberId, Long missionId);
}
