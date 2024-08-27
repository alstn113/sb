package com.sb.infra.auth.oauth.github;


import com.sb.application.auth.oauth.OAuthStrategy;
import com.sb.application.auth.oauth.OAuthUserDetails;
import com.sb.domain.member.OAuthProvider;
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

    public String getAccessToken(String code) {
        GithubAccessTokenResponse response = githubOAuthClient.getAccessToken(code);

        return response.accessToken();
    }

    public OAuthUserDetails getUserDetails(String accessToken) {
        GithubUserDetails githubUserDetails = githubOAuthClient.getUserDetails(accessToken);

        return githubUserDetails.toOAuthUserDetails();
    }

    public String getClientRedirectUri(String next) {
        return UriComponentsBuilder.fromHttpUrl(properties.clientUri())
                .path(next)
                .build()
                .toUriString();
    }

    public OAuthProvider getProviderType() {
        return OAuthProvider.GITHUB;
    }
}
