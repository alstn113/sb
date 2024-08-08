package com.sb.domain.solution;

import java.time.LocalDate;
import java.util.Optional;
import com.sb.domain.BaseEntity;
import com.sb.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Solution solution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @Column
    private LocalDate deletedAt;

    protected Comment() {
    }

    public Comment(String content, Solution solution, Member member) {
        this(null, content, solution, member, null, null);
    }

    public Comment(Long id, String content, Solution solution, Member member, Comment parent, LocalDate deletedAt) {
        super(id);
        this.content = content;
        this.solution = solution;
        this.member = member;
        this.parent = parent;
        this.deletedAt = deletedAt;
    }

    /**
     * 댓글은 2단계까지만 지원한다. (대댓글은 1단계까지만 가능), 3단계 이상은 대댓글로 처리한다.
     */
    public static Comment reply(String content, Solution solution, Member member, Comment parent) {
        Comment reply = new Comment(content, solution, member);

        Optional<Comment> rootComment = Optional.of(parent.parent);
        reply.parent = rootComment.orElse(parent);

        return reply;
    }

    public void delete() {
        deletedAt = LocalDate.now();
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isNotOwnedBy(Long memberId) {
        return !member.getId().equals(memberId);
    }

    public String getContent() {
        return content;
    }

    public Solution getSolution() {
        return solution;
    }

    public Member getMember() {
        return member;
    }

    public Comment getParent() {
        return parent;
    }

    public Long getParentId() {
        return parent.getId();
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }
}
