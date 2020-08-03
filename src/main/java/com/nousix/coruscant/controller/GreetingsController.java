package com.nousix.coruscant.controller;

import com.nousix.coruscant.controller.dto.GreetingDTO;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class GreetingsController {

    private static final Logger log = LoggerFactory.getLogger(GreetingsController.class);

    @Value("${GREETING_WORD:Hello}")
    String greetingWord;

    @Value("${MY_NAME:World}")
    String name;

    @Value("${MY_BROTHER:none}")
    String brotherService;

    @Value("${SECRET_ONE:Two kids}")
    String secretOne;

    @Value("${SECRET_TWO:Married}")
    String secretTwo;

    @GetMapping(value = "/hello")
    public GreetingDTO sayHello(@RequestParam(required = false) Boolean propagate) {
        log.info("Entering the greeting controller");
        GreetingDTO brother = null;
        if (!brotherService.equals("none") && (propagate == null || propagate )) {
            String url = "http://" + brotherService + "/hello?propagate=false";
            try {
                log.info("Calling brother at url: " + url);
                HttpResponse<GreetingDTO> greetingDTOHttpResponse = Unirest.get(url).asObject(GreetingDTO.class);
                if (greetingDTOHttpResponse.isSuccess()) {
                    brother = greetingDTOHttpResponse.getBody();
                } else {
                    brother = new GreetingDTO("ERROR", "url", Arrays.asList(String.valueOf(greetingDTOHttpResponse.getStatus()), greetingDTOHttpResponse.getStatusText()), retrieveHostname(), null);
                }
            } catch (Exception e) {
                log.error("Brother with error", e);
                List<String> secrets = Arrays.asList(e.getMessage(), e.toString());
                brother = new GreetingDTO("ERROR", "url", secrets , retrieveHostname(), null);
            }
            log.info("jola" );
            brother.setUrl(url);
        }
        return new GreetingDTO(greetingWord, name, Arrays.asList(secretOne, secretTwo), retrieveHostname(), brother);
    }

    private static String retrieveHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "Couldn't get hostname";
        }
    }

}
