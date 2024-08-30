package com.sb.domain.discussion;

import com.sb.infra.persistence.CreatedAtAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Discussion extends CreatedAtAuditableEntity {

    @Column(nullable = false)
    private String title;

    public Discussion(String title) {
        this(null, title);
    }

    public Discussion(Long id, String title) {
        super(id);
        this.title = title;
    }
}
