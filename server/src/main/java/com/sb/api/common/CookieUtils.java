package com.sb.api.common;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

public class CookieUtils {

    private static final String TOKEN_COOKIE_NAME = "token";
    private static final int COOKIE_MAX_AGE_ONE_DAY = 60 * 60 * 24;
    private static final List<String> DOMAINS = List.of(".alstn113.pro", "localhost");

    private CookieUtils() {
    }

    public static void setTokenCookie(HttpServletResponse response, String token) {
        DOMAINS.forEach(domain -> {
            ResponseCookie cookie = ResponseCookie.from(TOKEN_COOKIE_NAME, token)
                    .maxAge(COOKIE_MAX_AGE_ONE_DAY)
                    .httpOnly(true)
                    .secure(true)
                    .domain(domain)
                    .path("/")
                    .build();

            response.setHeader("Set-Cookie", cookie.toString());
        });
    }

    public static void clearTokenCookie(HttpServletResponse response) {
        DOMAINS.forEach(domain -> {
            ResponseCookie cookie = ResponseCookie.from(TOKEN_COOKIE_NAME, "")
                    .maxAge(0)
                    .httpOnly(true)
                    .secure(true)
                    .domain(domain)
                    .path("/")
                    .build();

            response.setHeader("Set-Cookie", cookie.toString());
        });
    }
}
