package com.sb.domain.mission;

import java.util.List;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            SELECT distinct m
            FROM Mission m
            JOIN FETCH m.missionTags mt
            JOIN FETCH mt.tag
            WHERE EXISTS (
                SELECT 1
                FROM MissionTag mt2
                JOIN mt2.tag t
                WHERE mt2.mission = m and t.name = :tagName
            )
            """)
    List<Mission> findAllByTagName(String tagName);

    default Mission getMissionById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SbException(ExceptionType.MISSION_NOT_FOUND));
    }
}
