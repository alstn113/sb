package com.sb.application.auth;


import com.sb.application.member.MemberResponse;
import com.sb.application.member.MemberService;
import com.sb.domain.member.Provider;
import com.sb.infra.auth.oauth.github.GithubOAuthProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final GithubOAuthProvider githubOAuthProvider;

    public AuthService(
            MemberService memberService,
            TokenProvider tokenProvider,
            GithubOAuthProvider githubOAuthProvider
    ) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
        this.githubOAuthProvider = githubOAuthProvider;
    }

    public String getGithubOAuthLoginUrl(String next) {
        return githubOAuthProvider.getLoginUrl(next);
    }

    @Transactional
    public String githubOAuthLogin(String code) {
        String accessToken = githubOAuthProvider.getAccessToken(code);
        OAuthUserInfo userInfo = githubOAuthProvider.getUserInfo(accessToken);

        MemberResponse memberResponse = memberService.findOrCreateMember(userInfo, Provider.GITHUB);
        return tokenProvider.createToken(memberResponse.id().toString());
    }

    public String getClientRedirectUri(String next) {
        return githubOAuthProvider.getClientRedirectUri(next);
    }

    public Long getMemberIdByToken(String token) {
        return tokenProvider.getMemberId(token);
    }
}
