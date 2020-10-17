package br.com.ac.servicea.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("servicec")
public class ServicecApi {

    private static Logger logger = LoggerFactory.getLogger(ServicecApi.class);

    @GetMapping("/message")
    public String getMessage() throws InterruptedException {
        logger.info("Hello from service C");
        Thread.sleep(500);
        return "It's me ServiceC!";
    }
}
