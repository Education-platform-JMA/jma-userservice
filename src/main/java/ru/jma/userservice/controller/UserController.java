package ru.jma.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.jma.userservice.model.User;
import ru.jma.userservice.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Flux<User> getAll() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public Mono<User> save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/users/{id}")
    public Mono<User> update(@PathVariable("id") Long id,
                             @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

}
