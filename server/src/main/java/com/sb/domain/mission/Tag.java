package com.sb.domain.mission;

import com.sb.infra.persistence.IdentifiableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Tag extends IdentifiableEntity {

    @Column(nullable = false)
    private String name;

    public Tag(String name) {
        this(null, name);
    }

    public Tag(Long id, String name) {
        super(id);
        this.name = name;
    }
}
