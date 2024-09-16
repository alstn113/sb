package com.sb.infra.auth.oauth.github.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sb.application.auth.oauth.OAuthUserDetails;
import jakarta.annotation.Nullable;

@JsonNaming(SnakeCaseStrategy.class)
public record GithubUserDetailsResponse(
        Long id,
        String login,
        String avatarUrl,
        @Nullable String email,
        @Nullable String name
) {

    public OAuthUserDetails toOAuthUserDetails() {
        String displayName = name;
        if (displayName == null || displayName.isBlank()) {
            displayName = login;
        }

        return new OAuthUserDetails(
                id,
                email,
                login,
                displayName,
                avatarUrl
        );
    }
}
