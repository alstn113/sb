package com.sb.application.auth;


import com.sb.application.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;

    public Long getMemberIdByToken(String token) {
        return tokenProvider.getMemberId(token);
    }
}
