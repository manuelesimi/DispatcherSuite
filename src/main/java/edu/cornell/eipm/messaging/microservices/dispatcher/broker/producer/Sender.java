package edu.cornell.eipm.messaging.microservices.dispatcher.broker.producer;

import edu.cornell.eipm.messaging.microservices.dispatcher.executors.JSONPayloadSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

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
   * @param values
   */
  public void send(String topic, Map<String, String> values) {
    LOGGER.info("sending payload='{}'", values);
    kafkaTemplate.send(topic, new JSONPayloadSerializer(values).toJSON());
  }
}
