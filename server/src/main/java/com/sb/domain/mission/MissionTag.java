package com.sb.domain.mission;

import com.sb.infra.persistence.IdentifiableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class MissionTag extends IdentifiableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    public MissionTag(Mission mission, Tag tag) {
        this(null, mission, tag);
    }

    public MissionTag(Long id, Mission mission, Tag tag) {
        super(id);
        this.mission = mission;
        this.tag = tag;
    }

    public boolean isSameTagName(String tagName) {
        return tag.getName().equals(tagName);
    }
}
