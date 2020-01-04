package edu.cornell.eipm.messaging.microservices.kafka.dispatcher.broker.producer;

import edu.cornell.eipm.messaging.microservices.executors.runtime.JSONPayloadSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
   * Sends the payload to the selected topic.
   * @param topic the target topic
   * @param values the values to send as message's payload
   */
  public void send(String topic, Map<String, String> values) {
    String json = new JSONPayloadSerializer(values).toJSON();
    LOGGER.info("sending payload='{}' to topic {}", json, topic);
    ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, topic, json);
    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

      @Override
      public void onSuccess(SendResult<String, String> result) {
        LOGGER.info("Sent message to =[" + topic +
                "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        LOGGER.error("Unable to send message to =["
                + topic + "] due to : " + ex.getMessage());
      }
    });

  }
}
