package com.sb.domain.solution;

import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    @Query(value = """
            SELECT EXISTS(
                SELECT 1
                FROM solution
                WHERE member_id = :memberId
                  AND mission_id = :missionId
                  AND submitted_at IS NULL
            )
            """, nativeQuery = true)
    boolean existsInProgressSolution(Long memberId, Long missionId);

    default Solution getSolutionById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SbException(ExceptionType.SOLUTION_NOT_FOUND));
    }
}
