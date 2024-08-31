package com.sb.domain.member;

import java.util.Optional;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;

public enum OAuthProvider {

    GITHUB,
    ;

    public static OAuthProvider from(String provider) {
        return Optional.ofNullable(provider)
                .map(String::toUpperCase)
                .map(OAuthProvider::valueOf)
                .orElseThrow(() -> new SbException(ExceptionType.OAUTH_PROVIDER_NOT_FOUND));
    }
}
