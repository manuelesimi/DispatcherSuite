package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Main app for Dispatcher.
 *
 * @author Manuele Simi
 */
@SpringBootApplication
public class DispatcherApp {

    private final Logger logger = LoggerFactory.getLogger(DispatcherApp.class);

    public static void main(String[] args) {
        SpringApplication.run(DispatcherApp.class, args);
    }

    @PostConstruct
    private void init() throws IOException {
        ConfigParser.parse();
    }


}
