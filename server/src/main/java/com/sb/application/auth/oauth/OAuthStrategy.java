package com.sb.application.auth.oauth;

import com.sb.domain.member.OAuthProvider;

public interface OAuthStrategy {

    String getLoginUrl(String next);

    String getAccessToken(String code);

    OAuthMemberDetails getUserInfo(String accessToken);

    String getClientRedirectUri(String next);

    OAuthProvider getProviderType();
}
