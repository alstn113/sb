package com.sb.domain.solution;

import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    @Query("""
            SELECT EXISTS(
                SELECT 1
                FROM Solution as s
                WHERE s.member.id = :memberId
                  AND s.mission.id = :missionId
                  AND s.submittedAt IS NULL
            )
            """)
    boolean existsInProgressSolution(Long memberId, Long missionId);


//    boolean existsByMember_IdAndMission_IdAndSubmittedAtIsNull(Long memberId, Long missionId);

    default Solution getSolutionById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SbException(ExceptionType.SOLUTION_NOT_FOUND));
    }
}
