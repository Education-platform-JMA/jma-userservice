package ru.jma.userservice.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.jma.dto.swagger.MessageDto;

import java.time.LocalDateTime;

@RestController
public class InteractionController {

    private final String INTERACTION_SERVICE_NAME = "COURSES";

    private EurekaClient eurekaClient;

    public InteractionController(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @GetMapping("/test")
    public Mono<MessageDto> getMyData() {
        return Mono.just(
                new MessageDto("hi! i'm test action in UserService", LocalDateTime.now(), false)
        );
    }

    @GetMapping("/test-remote")
    public Mono<MessageDto> getDataFromCourseService() {
        return WebClient.create(eurekaClient.getNextServerFromEureka(INTERACTION_SERVICE_NAME, false).getHomePageUrl())
                .get()
                .uri("test")
                .retrieve()
                .bodyToMono(MessageDto.class);
    }
}
