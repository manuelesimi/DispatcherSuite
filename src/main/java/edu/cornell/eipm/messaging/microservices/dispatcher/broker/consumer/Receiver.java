package edu.cornell.eipm.messaging.microservices.dispatcher.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

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

    public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "#{'${kafka.consumer.topics}'.split(',')}")
  public void receive(String payload) {
    LOGGER.info("received payload='{}'", payload);
    latch.countDown();
  }
}
