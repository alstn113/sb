package com.sb.api;

import java.io.IOException;
import com.sb.api.common.CookieUtils;
import com.sb.application.auth.oauth.OAuthService;
import com.sb.domain.member.OAuthProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi {

    private final OAuthService oauthService;

    @GetMapping("/api/v1/auth/social/redirect/{provider}")
    public void githubRedirect(
            @PathVariable OAuthProvider provider,
            @RequestParam(value = "next", defaultValue = "/") String next,
            HttpServletResponse response
    ) throws IOException {
        String redirectUri = oauthService.getOAuthLoginUrl(provider, next);
        response.sendRedirect(redirectUri);
    }

    @GetMapping("/api/v1/auth/social/callback/{provider}")
    public void githubCallback(
            @PathVariable OAuthProvider provider,
            @RequestParam("code") String code,
            @RequestParam(value = "next", defaultValue = "/") String next,
            HttpServletResponse response
    ) throws IOException {
        String token = oauthService.oauthLogin(provider, code);

        CookieUtils.setTokenCookie(response, token);

        String redirectUri = oauthService.getClientRedirectUrl(provider, next);
        response.sendRedirect(redirectUri);
    }

    @DeleteMapping("/api/v1/auth/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        CookieUtils.clearTokenCookie(response);

        return ResponseEntity.noContent().build();
    }
}
