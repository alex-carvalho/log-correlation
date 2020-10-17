package br.com.ac.serviceb.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ServiceCClient {

    private final WebClient webClient;
    private final String servicecUrl;

    public ServiceCClient(WebClient webClient, @Value("${servicec.url}") String servicecUrl) {
        this.webClient = webClient;
        this.servicecUrl = servicecUrl;
    }

    public Mono<String> getMessage() {
        return webClient.get()
                .uri(servicecUrl + "servicec/message")
                .retrieve()
                .bodyToMono(String.class);
    }
}
