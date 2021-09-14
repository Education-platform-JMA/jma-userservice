package ru.jma.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jma.userservice.model.User;
import ru.jma.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") Long id,
                           @RequestBody User userDetails) {
        Optional<User> user = userService.findUserById(id);
        user.get().setUsername(userDetails.getUsername());
        user.get().setPassword(userDetails.getPassword());
        return user.get();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

}
