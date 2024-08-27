package com.sb.application.member;

import com.sb.application.auth.oauth.OAuthMemberDetails;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.member.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse findOrCreateMember(OAuthMemberDetails memberDetails, OAuthProvider provider) {
        Member member = memberRepository.findBySocialIdAndProvider(memberDetails.id(), provider)
                .orElseGet(() -> memberRepository.save(memberDetails.toMember(provider)));

        return MemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.getMemberById(id);

        return MemberResponse.from(member);
    }
}
