package com.sb.application.auth.oauth;


import com.sb.domain.member.Member;
import com.sb.domain.member.OAuthProvider;
import jakarta.annotation.Nullable;

public record OAuthUserDetails(
        Long id,
        @Nullable String email,
        String username,
        @Nullable String displayName,
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
