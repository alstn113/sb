package com.sb.domain.solution.comment;

import java.time.LocalDateTime;
import java.util.Optional;
import com.sb.domain.CreatedAtAuditableEntity;
import com.sb.domain.member.Member;
import com.sb.domain.solution.Solution;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {
        @Index(name = "idx_solution_comment_solution_id", columnList = "solution_id"),
})
public class Comment extends CreatedAtAuditableEntity {

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Solution solution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @Column
    private LocalDateTime deletedAt;

    protected Comment() {
    }

    public Comment(String content, Solution solution, Member member) {
        this(null, content, solution, member, null, null);
    }

    public Comment(
            Long id,
            String content,
            Solution solution,
            Member member,
            Comment parentComment,
            LocalDateTime deletedAt
    ) {
        super(id);
        this.content = content;
        this.solution = solution;
        this.member = member;
        this.parentComment = parentComment;
        this.deletedAt = deletedAt;
    }

    /**
     * 댓글은 2단계까지만 지원한다. (대댓글은 1단계까지만 가능), 3단계 이상은 대댓글로 처리한다.
     */
    public static Comment reply(String content, Solution solution, Member member, Comment parentComment) {
        Comment reply = new Comment(content, solution, member);

        Optional<Comment> rootComment = Optional.ofNullable(parentComment.parentComment);
        reply.parentComment = rootComment.orElse(parentComment);

        return reply;
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }

    public boolean isRoot() {
        return parentComment == null;
    }

    public boolean isNotOwnedBy(Long memberId) {
        return !member.getId().equals(memberId);
    }

    public Long getParentCommentId() {
        return parentComment.getId();
    }

    public String getContent() {
        return content;
    }

    public Solution getSolution() {
        return solution;
    }

    public Long getSolutionId() {
        return solution.getId();
    }

    public Member getMember() {
        return member;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
