package ru.jma.userservice.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserRolesJwtAuthenticationConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {
    private final ReactiveUserDetailsService reactiveUserDetailsService;

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
        return reactiveUserDetailsService
                .findByUsername(jwt.getClaimAsString("username"))
                .map(u -> new UsernamePasswordAuthenticationToken(u, "n/a", u.getAuthorities()));
    }
}
