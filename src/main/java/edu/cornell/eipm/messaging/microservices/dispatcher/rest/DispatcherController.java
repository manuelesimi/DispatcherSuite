package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.ConfiguredActions;
import edu.cornell.eipm.messaging.microservices.dispatcher.config.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Rest Controller for the Dispatcher service.
 *
 * @author Manuele Simi
 */
@RestController
public class DispatcherController {

    @RequestMapping("/")
    public String home() {
        return "Hello from the Dispatcher Microservice";
    }

    @RequestMapping("/configuration")
    public String config() throws IOException {
        ConfigParser.parse();
        return ConfigAccess.configToString();
    }

    @RequestMapping("/configuration/topics")
    public Set<String> configTopics() throws IOException {
        return ConfigAccess.getTopicNames();
    }

    @RequestMapping("/configuration/messages")
    public List<String> configMessages(@RequestParam(required = true, value="topic") String topic) throws IOException {
        if (topic.isEmpty())
            return Collections.emptyList();
        return ConfigAccess.getMessages(topic);
    }

    @RequestMapping("/dispatch")
    public String dispatch(@RequestParam(required = true, value="topic") String topic,
                                @RequestParam(required = true, value="message") String message,
                                @RequestParam(required = false, value="payload") String payload
            ) throws IOException {
        if (topic.isEmpty()|| message.isEmpty())
            return "Topic and/or message cannot be empty";
        String trigger = ConfigAccess.getTrigger(topic,message,payload);
        return "About to launch: " +trigger;
    }

    @RequestMapping("/publish")
    public String publish(@RequestParam(required = true, value="topic") String topic,
                           @RequestParam(required = true, value="message") String message,
                           @RequestParam(required = false, value="payload") String payload
    ) throws IOException {
        if (topic.isEmpty()|| message.isEmpty())
            return "Topic and/or message cannot be empty";
        return "About to publish: " + payload;
    }
    @RequestMapping("/about")
    public About greeting(@RequestParam(value="name", defaultValue="client") String name) {
        return new About(name);
    }
}
