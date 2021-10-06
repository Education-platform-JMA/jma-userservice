package ru.jma.userservice;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.jma.userservice.controller.UserController;
import ru.jma.userservice.model.User;
import ru.jma.userservice.service.UserService;

@WebFluxTest(UserController.class)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserControllerTest {

    private final WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    private static User user;

    @BeforeAll
    static void initUsers() {
        user = new User((long) 1, "user", "1234");
    }

    @Test
    void testGetUser() {
        Flux<User> userFlux = Flux.just(user);
        Mockito.when(userService.getAllUsers()).thenReturn(userFlux);
        webTestClient.get()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].username").isEqualTo("user")
                .jsonPath("$[0].password").isEqualTo("1234");
    }

    @Test
    void testPostUser() {
        Mono<User> userMono = Mono.just(user);
        Mockito.when(userService.addUser(user)).thenReturn(userMono);
        webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMono, User.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class).isEqualTo(user);
    }

}
