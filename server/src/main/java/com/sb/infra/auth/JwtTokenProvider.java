package com.sb.infra.auth;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import com.sb.application.auth.TokenProvider;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final SecretKey secretKey;
    private final Long expirationTime;

    public JwtTokenProvider(JwtTokenProperties properties) {
        this.secretKey = Keys.hmacShaKeyFor(properties.secretKey().getBytes(StandardCharsets.UTF_8));
        this.expirationTime = properties.expirationTime();
    }

    @Override
    public String createToken(String memberId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(memberId)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public Long getMemberId(String token) {
        Claims claims = toClaims(token);

        String memberId = claims.getSubject();
        return Long.parseLong(memberId);
    }

    private Claims toClaims(String token) {
        if (token == null || token.isBlank()) {
            throw new SbException(ExceptionType.TOKEN_NOT_FOUND);
        }

        try {
            Jws<Claims> claimsJws = getClaimsJws(token);

            return claimsJws.getPayload();
        } catch (ExpiredJwtException e) {
            throw new SbException(ExceptionType.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new SbException(ExceptionType.INVALID_TOKEN);
        }
    }

    private Jws<Claims> getClaimsJws(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }
}
