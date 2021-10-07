package ru.jma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.jma.userservice.model.User;
import ru.jma.userservice.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public Flux<User> getAllUsers() {
        log.info("GET-request '/users' was received");
        return userService.getAllUsers();
    }

    @PostMapping
    public Mono<User> addUser(@RequestBody User user) {
        log.info("POST-request '/users' was received");
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@PathVariable("id") Long id,
                                 @RequestBody User user) {
        log.info("PUT-request '/users/{id}' was received");
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        log.info("DELETE-request '/users/{id}' was received");
        return userService.deleteUserById(id);
    }

}
