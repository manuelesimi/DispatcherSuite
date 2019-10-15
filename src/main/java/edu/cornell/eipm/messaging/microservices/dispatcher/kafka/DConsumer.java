package edu.cornell.eipm.messaging.microservices.dispatcher.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.CountDownLatch;

/**
 * Configurable Kafka consumer for plain strings as payload.
 *
 * @author Manuele Simi
 */
public class DConsumer {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "#{'${spring.kafka.topics}'.split(',')}")
    public void receive(String payload) {
        LOGGER.info("received payload='{}'", payload);
        latch.countDown();
    }
}