package com.sb.domain.comment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.sb.domain.BaseEntity;
import com.sb.domain.member.Member;
import com.sb.domain.solution.Solution;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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

    @OneToMany(mappedBy = "parent")
    private final List<Comment> replies = new ArrayList<>();

    @Column
    private LocalDate deletedAt;

    protected Comment() {
    }

    public Comment(String content, Solution solution, Member member) {
        this.content = content;
        this.solution = solution;
        this.member = member;
    }

    /**
     * 댓글은 2단계까지만 지원한다. (대댓글은 1단계까지만 가능), 3단계 이상은 대댓글로 처리한다.
     */
    public static Comment reply(String content, Solution solution, Member member, Comment parent) {
        Comment comment = new Comment(content, solution, member);

        Optional<Comment> rootComment = Optional.ofNullable(parent.parent);
        comment.parent = rootComment.orElse(parent);

        parent.replies.add(comment);

        return comment;
    }

    public void delete() {
        deletedAt = LocalDate.now();
    }

    public boolean isDeleted() {
        return deletedAt != null;
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

    public List<Comment> getReplies() {
        return replies;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }
}
