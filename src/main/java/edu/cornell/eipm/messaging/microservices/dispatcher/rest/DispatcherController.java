package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Rest Controller for the Dispatcher service.
 *
 * @author Manuele Simi
 */
@RestController
public class DispatcherController {

    @RequestMapping("/")
    public String home() {
        return "Hello from the Microservice Dispatcher";
    }

    @RequestMapping("/configuration")
    public String config() throws IOException {
        ConfigParser.parse();
        return ConfigParser.configToString();
    }

    @RequestMapping("/configuration/topics")
    public Set<String> configTopics() throws IOException {
        ConfigParser.parse();
        return ConfigParser.getConfig().getTopics();
    }

    @RequestMapping("/about")
    public About greeting(@RequestParam(value="name", defaultValue="client") String name) {
        return new About(name);
    }
}
