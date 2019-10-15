package edu.cornell.eipm.messaging.microservices.dispatcher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 *
 * Generic publisher.
 *
 * @author Manuele Simi
 */
public class DProducer {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String payload, String topic) {
        LOGGER.info("sending payload='{}'", payload);
        kafkaTemplate.send(topic, payload);
    }
}
