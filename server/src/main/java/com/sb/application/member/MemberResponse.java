package com.sb.application.member;

public record MemberResponse(
        Long id,
        String email,
        String username,
        String displayName,
        String avatarUrl
) {
}
