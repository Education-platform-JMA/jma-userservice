package ru.jma.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.jma.userservice.model.User;
import ru.jma.userservice.service.UserService;

@RestController
public class UserController {

    UserService userService;

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
    public Mono<ResponseEntity<User>> update(@PathVariable("id") Long id,
                                                 @RequestBody User userDetails) {
        User user = new User();
        return userService.findById(id).flatMap(userData -> {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            return userService.save(userData);
        }).map(updateuser -> new ResponseEntity<>(updateuser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

}
