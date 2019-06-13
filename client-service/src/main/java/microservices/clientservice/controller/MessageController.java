package microservices.clientservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RefreshScope
public class MessageController {
    @Value("${server.port}")
    String port;

    @GetMapping("/")
    public String message() {
        return "Hi, my port " + port;
    }

    @RequestMapping(value = "/greeting")
    public String greet() {
        List<String> greetings = Arrays.asList("Hi, Ribbon! ", "Good evening! ", "Hello! ");
        Random rand = new Random();
        int randomNum = rand.nextInt(greetings.size());
        String mess = greetings.get(randomNum) + "I'm " + port;
        return mess;
    }
}
