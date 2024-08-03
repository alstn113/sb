package com.sb.application.member;

import com.sb.application.auth.OAuthUserInfo;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.member.Provider;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberResponse findOrCreateMember(OAuthUserInfo oAuthUserInfo, Provider provider) {
        Member member = memberRepository.findBySocialIdAndProvider(oAuthUserInfo.id(), provider)
                .orElseGet(() -> memberRepository.save(oAuthUserInfo.toMember(provider)));

        return memberMapper.toResponse(member);
    }

    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.getMemberById(id);

        return memberMapper.toResponse(member);
    }
}
