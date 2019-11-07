package edu.cornell.eipm.messaging.microservices.dispatcher.broker.consumer;

import edu.cornell.eipm.messaging.microservices.dispatcher.broker.producer.Sender;
import edu.cornell.eipm.messaging.microservices.dispatcher.config.Reply;
import edu.cornell.eipm.messaging.microservices.dispatcher.config.TopicConfigurations;
import edu.cornell.eipm.messaging.microservices.dispatcher.executors.ExecutorService;
import edu.cornell.eipm.messaging.microservices.dispatcher.executors.JSONPayloadDeserializer;
import edu.cornell.eipm.messaging.microservices.dispatcher.executors.ReplyPayloadParser;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * Receive string payloads from selected topics.
 *
 * @author Manuele Simi
 */
public class Receiver {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @Value("${kafka.consumer.topics}")
    private String[] topics;

    @Value("${kafka.consumer.group-id}")
    private String groupId;

    @Autowired
    private Sender sender;

    @Autowired
    private TopicConfigurations topicConfigurations;

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(id = "#{'${kafka.consumer.group-id}'}",
            clientIdPrefix = "receiver",
            topics = "#{'${kafka.consumer.topics}'.split(',')}")
    public void receive(List<ConsumerRecord<?, String>> messages) {

        LOGGER.info("-----start of batch receive-----");
        for (int i = 0; i < messages.size(); i++) {
            ConsumerRecord<?, String> message = messages.get(i);
            LOGGER.info("Received messages on topic [{}]: [{}] ",
                    message.topic(), message.value());
            topicConfigurations.getActions(message.topic()).forEach( action -> {
                try {
                    Map<String, String> values = new JSONPayloadDeserializer(message.value()).fromJSON();
                    ExecutorService.select(action).execute(new JSONPayloadDeserializer(message.value()).fromJSON());
                    Reply actionReply = action.getReply();
                    if (Objects.nonNull(actionReply.getTopic()))
                        sender.send(actionReply.getTopic(),ReplyPayloadParser.parse(actionReply.getPayload(), values));
                } catch (IOException e) {
                    LOGGER.error("Failed to dispatch action '{}'",action.getTrigger(),e);
                }
            });
        }
        LOGGER.info("-----end of batch receive-----");
        latch.countDown();
    }
}
