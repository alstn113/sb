package com.sb.domain.member;

import java.util.Arrays;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;

public enum OAuthProvider {

    GITHUB,
    ;

    public static OAuthProvider from(String provider) {
        return Arrays.stream(values())
                .filter(oauthProvider -> oauthProvider.name().equalsIgnoreCase(provider))
                .findFirst()
                .orElseThrow(() -> new SbException(ExceptionType.OAUTH_PROVIDER_NOT_FOUND));
    }
}
