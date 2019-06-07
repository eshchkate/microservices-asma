package microservices.resourceservice.controller;

import microservices.resourceservice.model.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class DataController {

    private static Data[] datas = new Data[4];

    @PostConstruct
    public void initIt() {
        datas[0] = new Data(Long.valueOf(1), "Some data");
        datas[1] = new Data(Long.valueOf(2), "Some data");
        datas[2] = new Data(Long.valueOf(3), "Some data");
        datas[3] = new Data(Long.valueOf(4), "Some data");
    }

    @RequestMapping(value = "/data1", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('data') and #oauth2.hasScope('read')")
    public String[] getData() {
        String[] datas = new String[]{"Data 1", "Data 2", "Data 3"};

        return datas;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('data') and #oauth2.hasScope('read')")
    public Data[] getDatas() {
        initIt();
        return datas;
    }
}
