package com.sb.infra.auth.oauth.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sb.application.auth.oauth.OAuthMemberDetails;
import jakarta.annotation.Nullable;

@JsonNaming(SnakeCaseStrategy.class)
public record GithubUserInfo(
        Long id,
        String login,
        String avatarUrl,
        @Nullable String email,
        String name
) {

    public OAuthMemberDetails toOAuthUserInfo() {
        return new OAuthMemberDetails(
                id,
                email,
                login,
                name,
                avatarUrl
        );
    }
}
