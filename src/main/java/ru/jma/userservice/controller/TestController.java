package ru.jma.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/1")
    public String test1() {
        log.info("request '/users/test/1' was sent");
        return "UserService: test endpoint 1";
    }

    @GetMapping("/2")
    public String test2() {
        log.info("request '/users/test/2' was sent");
        log.warn("request '/users/test/2' is deprecated!");
        return "UserService: test endpoint 2";
    }
}
