package com.sb.domain.discussion.comment;

import java.time.LocalDateTime;
import com.sb.domain.discussion.Discussion;
import com.sb.domain.member.Member;
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
public class DiscussionComment extends CreatedAtAuditableEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private DiscussionComment parentComment;

    @Column
    private LocalDateTime deletedAt;

    public DiscussionComment(
            String content,
            Discussion discussion,
            Member member,
            DiscussionComment parentComment,
            LocalDateTime deletedAt
    ) {
        this(null, content, discussion, member, parentComment, deletedAt);
    }

    public DiscussionComment(
            Long id,
            String content,
            Discussion discussion,
            Member member,
            DiscussionComment parentComment,
            LocalDateTime deletedAt
    ) {
        super(id);
        this.content = content;
        this.discussion = discussion;
        this.member = member;
        this.parentComment = parentComment;
        this.deletedAt = deletedAt;
    }

    public static DiscussionComment create(String content, Discussion discussion, Member member) {
        return new DiscussionComment(content, discussion, member, null, null);
    }

    public DiscussionComment reply(String content, Member member) {
        if (this.isDeleted()) {
            throw new SbException(ExceptionType.COMMENT_ALREADY_DELETED);
        }

        if (this.isReply()) {
            throw new SbException(ExceptionType.CANNOT_REPLY_TO_REPLY);
        }

        DiscussionComment reply = new DiscussionComment();
        reply.content = content;
        reply.discussion = this.discussion;
        reply.member = member;
        reply.parentComment = this;

        return reply;
    }

    public void delete() {
        if (this.isDeleted()) {
            throw new SbException(ExceptionType.COMMENT_ALREADY_DELETED);
        }

        this.deletedAt = LocalDateTime.now();
    }

    public Long getDiscussionId() {
        return discussion.getId();
    }

    public boolean isNotWrittenBy(Long memberId) {
        return !member.getId().equals(memberId);
    }

    public Long getParentCommentId() {
        return parentComment.getId();
    }

    public boolean isRootComment() {
        return parentComment == null;
    }

    public boolean isReply() {
        return parentComment != null;
    }

    public boolean isNotDeleted() {
        return deletedAt == null;
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }
}
