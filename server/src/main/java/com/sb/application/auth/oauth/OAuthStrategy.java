package com.sb.application.auth.oauth;

import com.sb.domain.member.OAuthProvider;

public interface OAuthStrategy {

    String buildOAuthLoginUrl(String next);

    OAuthUserDetails fetchOAuthUserDetails(String code);

    String buildClientRedirectUrl(String next);

    OAuthProvider getProvider();
}
