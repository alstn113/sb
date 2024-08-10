package com.sb.application.member;

import com.sb.application.auth.OAuthUserInfo;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.member.Provider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse findOrCreateMember(OAuthUserInfo oAuthUserInfo, Provider provider) {
        Member member = memberRepository.findBySocialIdAndProvider(oAuthUserInfo.id(), provider)
                .orElseGet(() -> memberRepository.save(oAuthUserInfo.toMember(provider)));

        return MemberResponse.from(member);
    }

    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.getMemberById(id);

        return MemberResponse.from(member);
    }
}
