package com.sb.application.member;

import com.sb.domain.member.Member;

public record MemberResponse(
        Long id,
        String email,
        String username,
        String displayName,
        String avatarUrl
) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getUsername(),
                member.getDisplayName(),
                member.getAvatarUrl()
        );
    }
}
