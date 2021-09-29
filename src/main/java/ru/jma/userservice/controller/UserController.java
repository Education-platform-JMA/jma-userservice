package ru.jma.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.jma.userservice.model.User;
import ru.jma.userservice.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public Mono<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@PathVariable("id") Long id,
                                 @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }

}
