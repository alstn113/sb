package com.sb.domain.solution;

import java.time.LocalDateTime;
import com.sb.domain.BaseEntity;
import com.sb.domain.member.Member;
import com.sb.domain.mission.Mission;
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

    public void submit(String title, String description, String url) {
        if (submittedAt != null) {
            throw new IllegalStateException("이미 제출한 솔루션입니다.");
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
