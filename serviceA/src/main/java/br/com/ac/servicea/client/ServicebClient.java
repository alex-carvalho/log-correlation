package br.com.ac.servicea.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServicebClient {

    private final RestTemplate restTemplate;
    private final String servicebUrl;

    public ServicebClient(RestTemplate restTemplate, @Value("${serviceb.url}") String servicebUrl) {
        this.restTemplate = restTemplate;
        this.servicebUrl = servicebUrl;
    }

    public String getMessage() {
        return restTemplate.getForObject(servicebUrl + "/serviceb/message", String.class);
    }
}
