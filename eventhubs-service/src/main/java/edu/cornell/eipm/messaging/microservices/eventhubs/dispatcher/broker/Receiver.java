package edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher.broker;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

/**
 * @author Manuele Simi
 */
@EnableBinding(Sink.class)
public class Receiver {

    @StreamListener(Sink.INPUT)
    public void process(Message<?> message) {
        Acknowledgment acknowledgment = message.getHeaders()
                .get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            System.out.println("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
    }
}
