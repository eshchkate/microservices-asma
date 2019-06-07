package microservices.clientservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageController {

    @Value("${app.id}")
    String instance;

    @GetMapping("/")
    public String message() {
        return "Hi, I am <3   " + instance;
    }
}
