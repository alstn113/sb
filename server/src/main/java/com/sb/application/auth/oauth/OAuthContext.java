package com.sb.application.auth.oauth;

import com.sb.domain.member.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthContext {

    private final OAuthStrategyRegistry oauthStrategyRegistry;

    public String getOAuthLoginUrl(OAuthProvider provider, String next) {
        OAuthStrategy strategy = getOAuthStrategy(provider);
        return strategy.buildOAuthLoginUrl(next);
    }

    public OAuthUserDetails getOAuthUserDetails(OAuthProvider provider, String code) {
        OAuthStrategy strategy = getOAuthStrategy(provider);
        return strategy.fetchOAuthUserDetails(code);
    }

    public String getClientRedirectUrl(OAuthProvider provider, String next) {
        OAuthStrategy strategy = getOAuthStrategy(provider);
        return strategy.buildClientRedirectUrl(next);
    }

    private OAuthStrategy getOAuthStrategy(OAuthProvider provider) {
        return oauthStrategyRegistry.getProvider(provider)
                .orElseThrow(() -> new IllegalArgumentException("Unsupported OAuth provider: " + provider));
    }
}
