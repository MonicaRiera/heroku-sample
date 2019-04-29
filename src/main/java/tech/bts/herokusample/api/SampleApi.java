package tech.bts.herokusample.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class SampleApi {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from sample app";
    }
}
