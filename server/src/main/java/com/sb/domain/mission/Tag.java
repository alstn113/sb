package com.sb.domain.mission;

import com.sb.domain.IdentifiableEntity;
import jakarta.persistence.Column;

public class Tag extends IdentifiableEntity {

    @Column(nullable = false)
    private String name;

    protected Tag() {
    }

    public Tag(String name) {
        this(null, name);
    }

    public Tag(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
