package com.sb.domain.solution;

import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsByMember_IdAndMission_IdAndSubmittedAtIsNull(Long memberId, Long missionId);

    default Solution getSolutionById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SbException(ExceptionType.SOLUTION_NOT_FOUND));
    }
}
