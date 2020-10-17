package br.com.ac.servicea.api;

import br.com.ac.servicea.client.ServicebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicea")
public class ServiceaApi {

    private static Logger logger = LoggerFactory.getLogger(ServiceaApi.class);
    private final ServicebClient servicebClient;

    public ServiceaApi(ServicebClient servicebClient) {
        this.servicebClient = servicebClient;
    }

    @GetMapping("/message")
    public String getMessage() throws InterruptedException {
        logger.info("Hello from service A");
        Thread.sleep(500);
        final var result = String.format("ServiceA >> %s", servicebClient.getMessage());
        Thread.sleep(500);
        logger.info("Finish request");
        return result;
    }
}
