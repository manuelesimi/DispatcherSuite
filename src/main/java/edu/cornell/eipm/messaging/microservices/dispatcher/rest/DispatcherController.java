package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatcherController {

    @RequestMapping("/")
    public String home() {
        return "Hello from the Microservice Dispatcher";
    }

    @RequestMapping("/about")
    public About greeting(@RequestParam(value="name", defaultValue="client") String name) {
        return new About(name);
    }
}
