package com.sb.application.auth.oauth;

import com.sb.domain.member.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthContext {

    private final OAuthStrategyRegistry oauthStrategyRegistry;

    public String getLoginUrl(OAuthProvider providerType, String next) {
        OAuthStrategy strategy = getOAuthStrategy(providerType);
        return strategy.getLoginUrl(next);
    }

    public OAuthUserDetails getUserDetails(OAuthProvider providerType, String code) {
        OAuthStrategy strategy = getOAuthStrategy(providerType);
        return strategy.getUserDetails(code);
    }

    public String getClientRedirectUri(OAuthProvider providerType, String next) {
        OAuthStrategy strategy = getOAuthStrategy(providerType);
        return strategy.getClientRedirectUri(next);
    }

    private OAuthStrategy getOAuthStrategy(OAuthProvider providerType) {
        return oauthStrategyRegistry.getProvider(providerType)
                .orElseThrow(() -> new IllegalArgumentException("Unsupported OAuth provider: " + providerType));
    }
}
