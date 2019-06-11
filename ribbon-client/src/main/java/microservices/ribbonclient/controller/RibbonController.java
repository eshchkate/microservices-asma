package microservices.ribbonclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    @HystrixCommand(fallbackMethod = "getFallbackConfigClient",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })
    @RequestMapping("/")
    public String callService() {
//        if (RandomUtils.nextBoolean())
//            throw new RuntimeException("Failed!");
        return restTemplate.getForEntity("http://client-service", String.class).getBody();
    }

    public String getFallbackConfigClient() {
        return "Service is unavailable";
    }

    @HystrixCommand(fallbackMethod = "getFallbackConfigClient2")
    @RequestMapping("/rest")
    public String callService2() {
        if (RandomUtils.nextBoolean())
            throw new RuntimeException("Failed!");
        return "Success " + port;
    }

    public String getFallbackConfigClient2() {
        return "Fail" + port;
    }

}
