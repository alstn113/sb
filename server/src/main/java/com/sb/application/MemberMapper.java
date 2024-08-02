package com.sb.application;

import com.sb.domain.Member;
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
