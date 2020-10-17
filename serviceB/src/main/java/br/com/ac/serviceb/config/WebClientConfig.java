package br.com.ac.serviceb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static Logger logger = LoggerFactory.getLogger(WebClientConfig.class);

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(loggingFilter())
                .build();
    }

    private ExchangeFilterFunction loggingFilter() {
        return (clientRequest, exchange) -> {
            logger.info("Start request {} to: {}", clientRequest.method(), clientRequest.url());
            return exchange
                    .exchange(clientRequest)
                    .doOnEach(clientResponseSignal -> {
                        ClientResponse response;
                        if ((response = clientResponseSignal.get()) != null) {
                            logger.info(String.format("Response with status %s from: %s", response.rawStatusCode(), clientRequest.url()));
                        }
                    });
        };
    }

}
