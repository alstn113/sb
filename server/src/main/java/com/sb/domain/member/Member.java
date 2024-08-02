package com.sb.domain.member;

import com.sb.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Member extends BaseEntity {

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

    protected Member() {
    }

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
        this.id = id;
        this.email = email;
        this.provider = provider;
        this.socialId = socialId;
        this.username = username;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public Provider getProvider() {
        return provider;
    }

    public Long getSocialId() {
        return socialId;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
