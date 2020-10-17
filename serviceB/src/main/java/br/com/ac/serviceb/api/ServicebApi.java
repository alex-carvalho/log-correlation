package br.com.ac.serviceb.api;

import br.com.ac.serviceb.client.ServiceCClient;
import br.com.ac.serviceb.client.ServiceDClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/serviceb")
public class ServicebApi {

    private static Logger logger = LoggerFactory.getLogger(ServicebApi.class);

    private final ServiceCClient servicecClient;
    private final ServiceDClient serviceDClient;

    public ServicebApi(ServiceCClient servicecClient, ServiceDClient serviceDClient) {
        this.servicecClient = servicecClient;
        this.serviceDClient = serviceDClient;
    }

    @GetMapping("/message")
    public Mono<String> getMessage() {
        logger.info("Hello from service B");
        return Mono.delay(Duration.ofMillis(500))
                .flatMap(time -> Mono.zip(servicecClient.getMessage(), serviceDClient.getMessage()))
                .map(tuplas -> String.format(" ServiceB >> ( %s - %s )",tuplas.getT1(), tuplas.getT2()));
    }
}
