package com.sb.application;

public record MemberResponse(
        long id,
        String email,
        String username,
        String displayName,
        String avatarUrl
) {
}
