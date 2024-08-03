package com.sb.domain.solution;

import java.time.LocalDateTime;
import com.sb.domain.BaseEntity;
import com.sb.domain.member.Member;
import com.sb.domain.mission.Mission;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Solution extends BaseEntity {

    @ManyToOne
    private Mission mission;

    @ManyToOne
    private Member member;

    @Column
    private String title;

    @Column
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
            String url
    ) {
        this(null, mission, member, title, description, url);
    }

    public Solution(
            Long id,
            Mission mission,
            Member member,
            String title,
            String description,
            String url
    ) {
        this.id = id;
        this.mission = mission;
        this.member = member;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public static Solution createInitialSolution(Mission mission, Member member) {
        return new Solution(mission, member, null, null, null);
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
