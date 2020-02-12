package edu.cornell.eipm.messaging.microservices.kafka.dispatcher;

import edu.cornell.eipm.messaging.microservices.executors.model.service.Action;
import edu.cornell.eipm.messaging.microservices.executors.runtime.JSONPayloadDeserializer;
import edu.cornell.eipm.messaging.microservices.kafka.dispatcher.broker.consumer.Receiver;
import edu.cornell.eipm.messaging.microservices.kafka.dispatcher.broker.producer.Sender;
import edu.cornell.eipm.messaging.microservices.kafka.dispatcher.config.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    private KafkaService kafkaService;

    @Autowired
    private Receiver receiver;

    @Autowired
    private Sender sender;

    @RequestMapping("/")
    public String home() {
        return "Hello from the Kafka-Dispatcher Service";
    }

    @RequestMapping("/configuration")
    public Set<String> config() throws IOException {
        return kafkaService.getService().getTopicNames();
    }

    @RequestMapping("/configuration/topics")
    public Set<String> configTopics() throws IOException {
        return kafkaService.getService().getTopicNames();
    }

    @RequestMapping("/configuration/actions")
    public List<Action> configMessages(@RequestParam(required = true, value="topic") String topic) throws IOException {
        if (topic.isEmpty())
            return Collections.emptyList();
        return kafkaService.getService().getActions(topic);
    }

    @RequestMapping("/dispatch")
    public DispatchReply dispatch(@RequestParam(required = true, value="topic") String topic,
                                @RequestParam(required = false, value="payload") String payload
            ) throws IOException {

        List<Action> actions = kafkaService.getService().getActions(topic);
        DispatchReply dispatchReply = new DispatchReply();
        actions.forEach(action -> dispatchReply.addTrigger(action.getTrigger().replace("${payload}",payload) ));
        actions.forEach(action -> dispatchReply.addReply(action.getReply().getTopic(), action.getReply().getPayload().replace("${payload}",payload) ));

        return dispatchReply;
    }

    @RequestMapping("/publish/{topic}")
    public String publish(@PathVariable(value="topic") String topic,
                          @RequestParam Map<String,String> allRequestParams
    ) throws IOException {
        if (topic.isEmpty())
            return "Topic cannot be empty";
        logger.info("Sending new message to topic: " + topic);
        logger.info("Parameters are " + allRequestParams.entrySet());

        sender.send(topic,allRequestParams);
        return "Sent payload " + allRequestParams.entrySet() + " to Topic " + topic;
    }

    @PostMapping(path="/publish-data/{topic}", consumes=MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String dpublish(@PathVariable(value="topic") String topic,
                           @RequestParam Map<String,String> unusedParams,
                           @RequestBody String body
    ) throws IOException {
        if (topic.isEmpty())
            return "Topic cannot be empty";
        logger.info("Sending new message to topic: " + topic);
        logger.info("Body is " + body);
        Map<String,String> allRequestParams = new JSONPayloadDeserializer(body).fromJSON();
        sender.send(topic,allRequestParams);
        return "Sent payload " + allRequestParams.entrySet() + " to Topic " + topic;
    }

    @RequestMapping("/about")
    public About about() {
        return new About();
    }
}
