package com.sb.application.member;

import com.sb.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getUsername(),
                member.getDisplayName(),
                member.getAvatarUrl()
        );
    }
}
