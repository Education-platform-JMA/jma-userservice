package ru.jma.userservice.service.impl;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.jma.userservice.service.InteractionService;

@Service
@RequiredArgsConstructor
public class CourseInteractionService implements InteractionService {

    @Value("${jma.service.course}")
    private String serviceName;

    private final EurekaClient eurekaClient;

    @Override
    public <T> Mono<T> getMono(String uri, Class<T> responseClass) {
        return getWebClient(eurekaClient, serviceName)
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseClass);
    }
}
