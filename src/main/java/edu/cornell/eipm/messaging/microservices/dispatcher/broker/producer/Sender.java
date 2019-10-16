package edu.cornell.eipm.messaging.microservices.dispatcher.broker.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Send string payloads to selected topics.
 *
 * @author Manuele Simi
 */
public class Sender {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Sender.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  /**
   * Sends the payload.
   * @param payload
   */
  public void send(String topic, String payload) {
    LOGGER.info("sending payload='{}'", payload);
    kafkaTemplate.send(topic, payload);
  }
}
