package com.sb.domain.mission;

import com.sb.infra.persistence.IdentifiableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MissionTag extends IdentifiableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    protected MissionTag() {
    }

    public MissionTag(Mission mission, Tag tag) {
        this(null, mission, tag);
    }

    public MissionTag(Long id, Mission mission, Tag tag) {
        super(id);
        this.mission = mission;
        this.tag = tag;
    }

    public Mission getMission() {
        return mission;
    }

    public Tag getTag() {
        return tag;
    }

    public String getTagName() {
        return tag.getName();
    }

    public boolean isSameTagName(String tagName) {
        return tag.getName().equals(tagName);
    }
}
