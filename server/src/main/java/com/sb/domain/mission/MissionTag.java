package com.sb.domain.mission;

import com.sb.domain.IdentifiableEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MissionTag extends IdentifiableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    public Mission getMission() {
        return mission;
    }

    public Tag getTag() {
        return tag;
    }
}
