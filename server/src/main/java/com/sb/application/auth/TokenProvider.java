package com.sb.application.auth;

public interface TokenProvider {

    String createToken(String memberId);

    Long getMemberId(String token);
}
