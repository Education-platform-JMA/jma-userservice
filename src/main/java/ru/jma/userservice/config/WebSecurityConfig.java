package ru.jma.userservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import ru.jma.userservice.security.jwt.UserJwtAuthenticationConverter;
import ru.jma.userservice.security.jwt.UserRolesJwtAuthenticationConverter;

@EnableWebFluxSecurity
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class WebSecurityConfig {
    private final ReactiveUserDetailsService reactiveUserDetailsService;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .csrf()
                .disable()
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
//                .jwtAuthenticationConverter(userJwtAuthenticationConverter());
                .jwtAuthenticationConverter(userRolesJwtAuthenticationConverter());
        return http.build();
    }

    @Bean
    public UserJwtAuthenticationConverter userJwtAuthenticationConverter() {
        return new UserJwtAuthenticationConverter(reactiveUserDetailsService);
    }

    @Bean
    public UserRolesJwtAuthenticationConverter userRolesJwtAuthenticationConverter() {
        return new UserRolesJwtAuthenticationConverter(reactiveUserDetailsService);
    }
}
