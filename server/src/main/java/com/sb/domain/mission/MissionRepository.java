package com.sb.domain.mission;

import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    default Mission getMissionById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SbException(ExceptionType.MISSION_NOT_FOUND));
    }
}
