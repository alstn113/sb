package com.sb.domain.solution;

import java.time.LocalDateTime;
import com.sb.domain.member.Member;
import com.sb.domain.mission.Mission;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import com.sb.infra.persistence.CreatedAtAuditableEntity;
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
public class Solution extends CreatedAtAuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String url;

    @Column
    private LocalDateTime submittedAt;

    public Solution(
            Mission mission,
            Member member,
            String title,
            String description,
            String url,
            LocalDateTime submittedAt
    ) {
        this(null, mission, member, title, description, url, submittedAt);
    }

    public Solution(
            Long id,
            Mission mission,
            Member member,
            String title,
            String description,
            String url,
            LocalDateTime submittedAt
    ) {
        super(id);
        this.mission = mission;
        this.member = member;
        this.title = title;
        this.description = description;
        this.url = url;
        this.submittedAt = submittedAt;
    }

    public static Solution start(Mission mission, Member member) {
        return new Solution(mission, member, null, null, null, null);
    }

    public void submit(String title, String description, String url) {
        if (submittedAt != null) {
            throw new SbException(ExceptionType.SOLUTION_ALREADY_SUBMITTED);
        }

        this.title = title;
        this.description = description;
        this.url = url;
        this.submittedAt = LocalDateTime.now();
    }

    public boolean isOwnedBy(Long memberId) {
        return getId().equals(memberId);
    }
}
