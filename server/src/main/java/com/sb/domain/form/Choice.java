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
    @JoinColumn(name = "question_option_id", nullable = false)
    private QuestionOption questionOption;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Choice(QuestionOption questionOption, Member member) {
        this(null, questionOption, member);
    }

    public Choice(Long id, QuestionOption questionOption, Member member) {
        super(id);
        this.questionOption = questionOption;
        this.member = member;
    }
}
