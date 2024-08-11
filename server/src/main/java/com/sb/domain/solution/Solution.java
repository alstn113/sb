package com.sb.domain.solution;

import java.time.LocalDateTime;
import com.sb.domain.CreatedAtAuditableEntity;
import com.sb.domain.member.Member;
import com.sb.domain.mission.Mission;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {
        @Index(name = "idx_solution_mission_id", columnList = "mission_id"),
})
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

    protected Solution() {
    }

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
        this.id = id;
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

    public Mission getMission() {
        return mission;
    }

    public Member getMember() {
        return member;
    }

    public boolean isOwnedBy(Long memberId) {
        return getId().equals(memberId);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
}
