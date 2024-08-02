package com.sb.application.member;

public record MemberResponse(
        long id,
        String email,
        String username,
        String displayName,
        String avatarUrl
) {
}
