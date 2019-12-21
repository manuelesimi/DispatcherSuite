package edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher.broker;

import edu.cornell.eipm.messaging.microservices.executors.runtime.JSONPayloadSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

/**
 * Senders for messages to selected topics.
 * @author Manuele Simi
 */
@EnableBinding(Source.class)
public class Sender {

    @Autowired
    private Source source;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Sender.class);

    /**
     * Sends the message to the selected topic.
     * @param topic
     * @param values
     */
    public void send(String topic, Map<String, String> values) {
        String json = new JSONPayloadSerializer(values).toJSON();
        LOGGER.info("sending payload='{}' to topic {}", json, topic);
        MessageHeaders headers;
        //MessageBuilder.withPayload(json).setHeader().build();
        source.output().send(MessageBuilder.withPayload(json).build());
        //this.source.output().send(new GenericMessage<>(message));
    }
}
