package com.sb.domain.mission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    default Mission getById(long id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));
    }
}
