package ru.jma.userservice.service;

import com.netflix.discovery.EurekaClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public interface InteractionService {

    <T> Mono<T> getMono(String uri, Class<T> responseClass);

    default WebClient getWebClient(EurekaClient eurekaClient, String serviceName) {
        return WebClient.create(
                eurekaClient.getNextServerFromEureka(serviceName, false).getHomePageUrl()
        );
    }
}