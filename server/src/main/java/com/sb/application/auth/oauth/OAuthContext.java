package com.sb.application.auth.oauth;

import com.sb.domain.member.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthContext {

    private final OAuthStrategyRegistry oauthStrategyRegistry;

    public String getLoginUrl(OAuthProvider provider, String next) {
        OAuthStrategy strategy = getOAuthStrategy(provider);
        return strategy.getLoginUrl(next);
    }

    public OAuthUserDetails getUserDetails(OAuthProvider provider, String code) {
        OAuthStrategy strategy = getOAuthStrategy(provider);
        return strategy.getUserDetails(code);
    }

    public String getClientRedirectUri(OAuthProvider provider, String next) {
        OAuthStrategy strategy = getOAuthStrategy(provider);
        return strategy.getClientRedirectUri(next);
    }

    private OAuthStrategy getOAuthStrategy(OAuthProvider provider) {
        return oauthStrategyRegistry.getProvider(provider)
                .orElseThrow(() -> new IllegalArgumentException("Unsupported OAuth provider: " + provider));
    }
}
