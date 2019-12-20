package edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher;

import edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher.config.EventHubsService;
import edu.cornell.eipm.messaging.microservices.executors.model.service.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Rest Controller for the EventHubsApp service.
 *
 * @author Manuele Simi
 */
@RestController
public class EventHubsController {

    private final Logger logger = LoggerFactory.getLogger(EventHubsController.class);

    @Autowired
    private EventHubsService service;


    @RequestMapping("/")
    public String home() {
        return "Hello from the EventHubs-Dispatcher Service";
    }

    @RequestMapping("/configuration")
    public Set<String> config() throws IOException {
        return service.getService().getTopicNames();
    }

    @RequestMapping("/configuration/topics")
    public Set<String> configTopics() throws IOException {
        return service.getService().getTopicNames();
    }

    @RequestMapping("/configuration/actions")
    public List<Action> configMessages(@RequestParam(required = true, value="topic") String topic) throws IOException {
        if (topic.isEmpty())
            return Collections.emptyList();
        return service.getService().getActions(topic);
    }


    @RequestMapping("/publish/{topic}")
    public String publish(@PathVariable(value="topic") String topic,
                          @RequestParam Map<String,String> allRequestParams
    ) throws IOException {
        if (topic.isEmpty())
            return "Topic cannot be empty";
        logger.info("Sending new message to topic: " + topic);
        logger.info("Parameters are " + allRequestParams.entrySet());

        //sender.send(topic,allRequestParams);
        return "Sent payload " + allRequestParams.entrySet() + " to Topic " + topic;
    }

    @RequestMapping("/about")
    public About about() {
        return new About();
    }
}
