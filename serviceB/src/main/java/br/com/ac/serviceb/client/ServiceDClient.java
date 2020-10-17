package br.com.ac.serviceb.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ServiceDClient {

    private final WebClient webClient;
    private final String servicecUrl;

    public ServiceDClient(WebClient webClient, @Value("${serviced.url}") String servicecUrl) {
        this.webClient = webClient;
        this.servicecUrl = servicecUrl;
    }

    public Mono<String> getMessage() {
        return webClient.get()
                .uri(servicecUrl + "serviced/message")
                .retrieve()
                .bodyToMono(String.class);
    }
}
