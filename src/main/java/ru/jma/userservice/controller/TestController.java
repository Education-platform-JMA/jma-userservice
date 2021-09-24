package ru.jma.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/1")
    public String test1() {
        LOGGER.info("request '/users/test/1' was sent");
        return "UserService: test endpoint 1";
    }

    @GetMapping("/2")
    public String test2() {
        LOGGER.info("request '/users/test/2' was sent");
        LOGGER.warn("request '/users/test/2' is deprecated!");
        return "UserService: test endpoint 2";
    }
}
