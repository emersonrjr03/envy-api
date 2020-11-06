package br.com.egp.envy.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/healthcheck")
public class HealthCheckResource {

    @RequestMapping(method = RequestMethod.GET)
    public String healthcheck() {
        return "WORKING";
    }
}
