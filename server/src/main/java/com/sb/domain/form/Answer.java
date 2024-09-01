package com.sb.domain.form;


import com.sb.domain.member.Member;
import com.sb.infra.persistence.IdentifiableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Answer extends IdentifiableEntity {

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Answer(String text, Question question, Member member) {
        this(null, text, question, member);
    }

    public Answer(Long id, String text, Question question, Member member) {
        super(id);
        this.text = text;
        this.question = question;
        this.member = member;
    }
}
