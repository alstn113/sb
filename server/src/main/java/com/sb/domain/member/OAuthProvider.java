package com.sb.domain.member;

import java.util.Optional;

public enum OAuthProvider {

    GITHUB,
    ;

    public static OAuthProvider from(String provider) {
        return Optional.ofNullable(provider)
                .map(String::toUpperCase)
                .map(OAuthProvider::valueOf)
                .orElseThrow(() -> new IllegalArgumentException("Unsupported OAuth provider: " + provider));
    }
}
