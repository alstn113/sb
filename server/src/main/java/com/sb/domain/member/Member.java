package com.sb.domain.member;

import com.sb.infra.persistence.CreatedAtAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Member extends CreatedAtAuditableEntity {

    @Column
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(nullable = false)
    private Long socialId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String avatarUrl;

    public Member(
            String email,
            Provider provider,
            Long socialId,
            String username,
            String displayName,
            String avatarUrl
    ) {
        this(null, email, provider, socialId, username, displayName, avatarUrl);
    }

    public Member(
            Long id,
            String email,
            Provider provider,
            Long socialId,
            String username,
            String displayName,
            String avatarUrl
    ) {
        super(id);
        this.email = email;
        this.provider = provider;
        this.socialId = socialId;
        this.username = username;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
    }
}
