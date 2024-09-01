package com.sb.domain.form;

import com.sb.infra.persistence.CreatedAtAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Form extends CreatedAtAuditableEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public Form(String title, String description) {
        this(null, title, description);
    }

    public Form(Long id, String title, String description) {
        super(id);
        this.title = title;
        this.description = description;
    }
}
