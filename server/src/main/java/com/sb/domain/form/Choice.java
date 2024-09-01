package com.sb.domain.form;

import com.sb.domain.member.Member;
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
public class Choice extends IdentifiableEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Choice(Question question, Answer answer, Member member) {
        this(null, question, answer, member);
    }

    public Choice(Long id, Question question, Answer answer, Member member) {
        super(id);
        this.question = question;
        this.answer = answer;
        this.member = member;
    }
}
