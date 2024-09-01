package com.sb.domain.form;

import com.sb.infra.persistence.IdentifiableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Option extends IdentifiableEntity {

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private int order;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

    public Option(String text, int order, Question question) {
        this(null, text, order, question);
    }

    public Option(Long id, String text, int order, Question question) {
        super(id);
        this.text = text;
        this.order = order;
        this.question = question;
    }
}
