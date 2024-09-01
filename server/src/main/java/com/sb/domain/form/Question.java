package com.sb.domain.form;

import com.sb.infra.persistence.IdentifiableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Question extends IdentifiableEntity {

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(nullable = false)
    private int order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    public Question(String text, QuestionType type, int order, Form form) {
        this(null, text, type, order, form);
    }

    public Question(Long id, String text, QuestionType type, int order, Form form) {
        super(id);
        this.text = text;
        this.type = type;
        this.order = order;
        this.form = form;
    }
}
