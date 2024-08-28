package com.sb.application.auth.oauth;

import com.sb.domain.member.OAuthProvider;

public interface OAuthStrategy {

    String getLoginUrl(String next);

    OAuthUserDetails getUserDetails(String code);

    String getClientRedirectUri(String next);

    OAuthProvider getProvider();
}
