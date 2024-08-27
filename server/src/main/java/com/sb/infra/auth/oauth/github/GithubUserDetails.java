package com.sb.infra.auth.oauth.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sb.application.auth.oauth.OAuthUserDetails;
import jakarta.annotation.Nullable;

@JsonNaming(SnakeCaseStrategy.class)
public record GithubUserDetails(
        Long id,
        String login,
        String avatarUrl,
        @Nullable String email,
        String name
) {

    public OAuthUserDetails toOAuthUserDetails() {
        return new OAuthUserDetails(
                id,
                email,
                login,
                name,
                avatarUrl
        );
    }
}
