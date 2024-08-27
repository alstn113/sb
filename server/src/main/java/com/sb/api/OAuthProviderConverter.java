package com.sb.api;

import com.sb.domain.member.OAuthProvider;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OAuthProviderConverter implements Converter<String, OAuthProvider> {

    @Override
    public OAuthProvider convert(String source) {
        return OAuthProvider.from(source);
    }
}
