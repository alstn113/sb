package com.sb.infra.auth.oauth.github;


import com.sb.application.auth.oauth.OAuthStrategy;
import com.sb.application.auth.oauth.OAuthUserDetails;
import com.sb.domain.member.OAuthProvider;
import com.sb.infra.auth.oauth.github.dto.GithubAccessTokenResponse;
import com.sb.infra.auth.oauth.github.dto.GithubUserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class GithubOAuthStrategy implements OAuthStrategy {

    private final GithubOAuthClient githubOAuthClient;
    private final GithubOAuthProperties properties;

    public String getLoginUrl(String next) {
        String redirectUriWithNext = UriComponentsBuilder.fromHttpUrl(properties.redirectUri())
                .queryParam("next", next)
                .build()
                .toUriString();

        return UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/authorize")
                .queryParam("client_id", properties.clientId())
                .queryParam("redirect_uri", redirectUriWithNext)
                .queryParam("scope", "user:email")
                .build()
                .toUriString();
    }

    public OAuthUserDetails getUserDetails(String code) {
        GithubAccessTokenResponse accessTokenResponse = githubOAuthClient.fetchAccessToken(code);
        String accessToken = accessTokenResponse.accessToken();

        GithubUserDetailsResponse userDetailsResponse = githubOAuthClient.fetchUserDetails(accessToken);

        return userDetailsResponse.toOAuthUserDetails();
    }

    public String getClientRedirectUri(String next) {
        return UriComponentsBuilder.fromHttpUrl(properties.clientUri())
                .path(next)
                .build()
                .toUriString();
    }

    public OAuthProvider getProvider() {
        return OAuthProvider.GITHUB;
    }
}
