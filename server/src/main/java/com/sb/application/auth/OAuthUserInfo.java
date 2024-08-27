package com.sb.application.auth;


import com.sb.domain.member.Member;
import com.sb.domain.member.OAuthProvider;
import jakarta.annotation.Nullable;

public record OAuthUserInfo(
        Long id,
        @Nullable String email,
        String username,
        String displayName,
        String avatarUrl
) {

    public Member toMember(OAuthProvider provider) {
        return new Member(
                email,
                provider,
                id,
                username,
                displayName,
                avatarUrl
        );
    }
}
