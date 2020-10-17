package br.com.ac.servicea.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .interceptors(new LoggingInterceptor())
                .build();
    }

    private static class LoggingInterceptor implements ClientHttpRequestInterceptor {
        private Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

        @Override
        public ClientHttpResponse intercept(HttpRequest clientRequest, byte[] reqBody, ClientHttpRequestExecution ex)
                throws IOException {
            logger.info("Start request {} to: {}", clientRequest.getMethod(), clientRequest.getURI());
            ClientHttpResponse response = ex.execute(clientRequest, reqBody);
            logger.info("Response with status {} from: {}", response.getRawStatusCode(), clientRequest.getURI());
            return response;
        }
    }



}