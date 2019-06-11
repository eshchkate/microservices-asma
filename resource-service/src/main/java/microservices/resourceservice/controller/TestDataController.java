package microservices.resourceservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.resourceservice.model.TestData;
import microservices.resourceservice.repos.TestDataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@RestController
public class TestDataController {

    private final static Logger logger = LoggerFactory.getLogger(TestDataController.class);

    private TestDataRepo testDataRepo;

    @Autowired
    public TestDataController(TestDataRepo testDataRepo) {
        this.testDataRepo = testDataRepo;
    }


    @RequestMapping("/json")
    public void json() {

        File jsonFile = null;
        try {
            jsonFile = ResourceUtils.getFile("classpath:testdata.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<TestData> testData = objectMapper.readValue(jsonFile, new TypeReference<List<TestData>>() {
            });

            testDataRepo.saveAll(testData);

            logger.info("All records saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('data')")
    public String getData() {
        String str = testDataRepo.findAll().toString();
        return str;
    }

/*
/*
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('data')")
    public String getData() {
        return testDataRepo.findAll().toString();
    }
*/
}
