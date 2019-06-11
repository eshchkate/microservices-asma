package microservices.clientservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageController {

    @Value("${server.port}")
    String port;

    @GetMapping("/")
    public String message() {
        return "Hi, my port " + port;
    }
}
