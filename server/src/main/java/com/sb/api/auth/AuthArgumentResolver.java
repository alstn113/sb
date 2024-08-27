package com.sb.api.auth;

import static java.util.Objects.requireNonNull;

import com.sb.api.common.CookieUtils;
import com.sb.application.auth.Accessor;
import com.sb.application.auth.AuthService;
import com.sb.application.member.MemberResponse;
import com.sb.application.member.MemberService;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final CookieAuthorizationExtractor authorizationExtractor;
    private final AuthService authService;
    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAuthAnnotation = parameter.hasParameterAnnotation(Auth.class);
        boolean isAccessorClass = Accessor.class.isAssignableFrom(parameter.getParameterType());

        return hasAuthAnnotation && isAccessorClass;
    }

    @Override
    public Accessor resolveArgument(
            @Nonnull MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            @NonNull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        Auth auth = requireNonNull(parameter.getParameterAnnotation(Auth.class));
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        String token = extractTokenFromCookie(webRequest);
        if (token == null) {
            return handleNoToken(auth);
        }

        return handleToken(token, response);
    }

    private String extractTokenFromCookie(NativeWebRequest webRequest) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            return null;
        }

        return authorizationExtractor.extract(request);
    }

    private Accessor handleNoToken(Auth auth) {
        if (auth.required()) {
            throw new SbException(ExceptionType.UNAUTHORIZED);
        }

        return Accessor.GUEST;
    }

    private Accessor handleToken(String token, HttpServletResponse response) {
        try {
            Long memberId = authService.getMemberIdByToken(token);
            MemberResponse memberResponse = memberService.getMemberById(memberId);

            return new Accessor(memberResponse.id());
        } catch (SbException e) {
            CookieUtils.clearTokenCookie(response);

            throw new SbException(ExceptionType.UNAUTHORIZED, e);
        }
    }
}
