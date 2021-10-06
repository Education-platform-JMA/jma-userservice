package ru.jma.userservice.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.jma.userservice.model.Role;
import ru.jma.userservice.model.User;

import java.util.Set;

@Service
public class MockReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.just(User.builder()
                .username("admin")
                .password("admin")
                .roles(Set.of(new Role("USER"), new Role("ADMIN")))
                .build());
    }
}
