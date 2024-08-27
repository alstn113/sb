package com.sb.domain.member;

public enum OAuthProvider {

    GITHUB,
    ;

    public static OAuthProvider from(String provider) {
        return OAuthProvider.valueOf(provider.toUpperCase());
    }
}
