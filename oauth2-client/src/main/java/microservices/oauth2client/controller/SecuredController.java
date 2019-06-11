package microservices.oauth2client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecuredController {

    @Value("${data.base-uri}")
    private String dataApiBaseUri;

    @Autowired
    @Qualifier("dataAppRestTemplate")
    private OAuth2RestTemplate dataAppRestTemplate;


    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String root() {
        return "redirect:/data/index";
    }

    @RequestMapping(value = "/data/index", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        String data = dataAppRestTemplate.getForObject(dataApiBaseUri, String.class);
        return data;
    }

}
