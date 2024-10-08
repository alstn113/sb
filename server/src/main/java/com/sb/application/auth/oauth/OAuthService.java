package com.sb.application.auth.oauth;

import com.sb.application.auth.TokenProvider;
import com.sb.application.member.MemberResponse;
import com.sb.application.member.MemberService;
import com.sb.domain.member.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuthContext oauthContext;
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    public String getOAuthLoginUrl(OAuthProvider provider, String next) {
        return oauthContext.getOAuthLoginUrl(provider, next);
    }

    public String oauthLogin(OAuthProvider provider, String code) {
        OAuthUserDetails userDetails = oauthContext.getOAuthUserDetails(provider, code);
        MemberResponse memberResponse = memberService.findOrCreateMember(userDetails, provider);
        return tokenProvider.createToken(memberResponse.id().toString());
    }

    public String getClientRedirectUrl(OAuthProvider provider, String next) {
        return oauthContext.getClientRedirectUrl(provider, next);
    }
}
