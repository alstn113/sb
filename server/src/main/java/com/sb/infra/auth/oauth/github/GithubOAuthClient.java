package com.sb.infra.auth.oauth.github;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.sb.infra.auth.oauth.github.dto.GithubAccessTokenRequest;
import com.sb.infra.auth.oauth.github.dto.GithubAccessTokenResponse;
import com.sb.infra.auth.oauth.github.dto.GithubUserDetailsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GithubOAuthClient {

    private final GithubOAuthProperties properties;
    private final RestClient restClient;

    public GithubOAuthClient(GithubOAuthProperties properties, RestClient.Builder restClientBuilder) {
        this.properties = properties;
        this.restClient = restClientBuilder.build();
    }

    public GithubAccessTokenResponse fetchAccessToken(String code) {
        GithubAccessTokenRequest request = new GithubAccessTokenRequest(
                code,
                properties.clientId(),
                properties.clientSecret()
        );

        return restClient.post()
                .uri("https://github.com/login/oauth/access_token")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(GithubAccessTokenResponse.class);
    }

    public GithubUserDetailsResponse fetchUserDetails(String accessToken) {
        return restClient.get()
                .uri("https://api.github.com/user")
                .header(AUTHORIZATION, String.format("Bearer %s", accessToken))
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(GithubUserDetailsResponse.class);
    }
}
