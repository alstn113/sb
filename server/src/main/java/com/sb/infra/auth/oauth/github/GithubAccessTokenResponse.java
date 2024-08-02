package com.sb.infra.auth.oauth.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record GithubAccessTokenResponse(String accessToken, String tokenType, String scope) {
}
