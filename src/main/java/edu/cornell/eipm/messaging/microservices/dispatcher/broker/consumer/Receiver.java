package edu.cornell.eipm.messaging.microservices.dispatcher.broker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;
import java.util.Map;
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

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(id = "#{'${kafka.consumer.group-id}'}",
            clientIdPrefix = "receiver",
            topics = "#{'${kafka.consumer.topics}'.split(',')}")
    public void receive(List<ConsumerRecord<?, Map<String, String>>> messages) {

        LOGGER.info("start of batch receive");
        for (int i = 0; i < messages.size(); i++) {
            ConsumerRecord<?, Map<String, String>> message = messages.get(i);
            LOGGER.info("Received messages on topic [{}]: [{}] '",
                    message.topic(), message.value());
            // TODO: handle the  message

            latch.countDown();
        }
        LOGGER.info("end of batch receive");
    }
}
