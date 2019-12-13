package edu.cornell.eipm.messaging.microservices.kafka.dispatcher;

import edu.cornell.eipm.messaging.microservices.kafka.dispatcher.config.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Main app for Dispatcher.
 *
 * @author Manuele Simi
 */
@SpringBootApplication
@EnableScheduling
public class DispatcherApp implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DispatcherApp.class);

    @Autowired
    private KafkaService kafkaService;

    public static void main(String[] args) {
        SpringApplication.run(DispatcherApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(kafkaService);
    }

    @PostConstruct
    private void init() throws IOException {
    }

}
