package edu.cornell.eipm.messaging.microservices.dispatcher;

import edu.cornell.eipm.messaging.microservices.dispatcher.broker.consumer.Receiver;
import edu.cornell.eipm.messaging.microservices.dispatcher.broker.producer.Sender;
import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import edu.cornell.eipm.messaging.microservices.dispatcher.config.TopicConfigurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Rest Controller for the DispatcherApp service.
 *
 * @author Manuele Simi
 */
@RestController
public class DispatcherController {

    private final Logger logger = LoggerFactory.getLogger(DispatcherController.class);

    @Autowired
    private TopicConfigurations topicConfigurations;

    @Autowired
    private Receiver receiver;

    @Autowired
    private Sender sender;

    @RequestMapping("/")
    public String home() {
        return "Hello from the DispatcherApp Microservice";
    }

    @RequestMapping("/configuration")
    public Set<String> config() throws IOException {
        return topicConfigurations.getTopicNames();
    }

    @RequestMapping("/configuration/topics")
    public Set<String> configTopics() throws IOException {
        return topicConfigurations.getTopicNames();
    }

    @RequestMapping("/configuration/actions")
    public List<Action> configMessages(@RequestParam(required = true, value="topic") String topic) throws IOException {
        if (topic.isEmpty())
            return Collections.emptyList();
        return topicConfigurations.getActions(topic);
    }

    @RequestMapping("/dispatch")
    public DispatchReply dispatch(@RequestParam(required = true, value="topic") String topic,
                                @RequestParam(required = false, value="payload") String payload
            ) throws IOException {

        List<Action> actions = topicConfigurations.getActions(topic);
        DispatchReply dispatchReply = new DispatchReply();
        actions.forEach(action -> dispatchReply.addTrigger(action.getTrigger().replace("${payload}",payload) ));
        actions.forEach(action -> dispatchReply.addReply(action.getReply().getTopic(), action.getReply().getPayload().replace("${payload}",payload) ));

        return dispatchReply;
    }

    @RequestMapping("/publish")
    public String publish(@RequestParam(required = true, value="topic") String topic,
                           @RequestParam(required = false, value="payload") String payload
    ) throws IOException {
        if (topic.isEmpty())
            return "Topic cannot be empty";
        sender.send(topic,payload);
        return "Sent payload " + payload + " tp Topic " + topic;
    }

    @RequestMapping("/about")
    public About greeting(@RequestParam(value="name", defaultValue="client") String name) {
        return new About(name);
    }
}
