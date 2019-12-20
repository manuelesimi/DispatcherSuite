package edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher;

import edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher.config.EventHubsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Main app for the EventHubs Service.
 *
 * @author Manuele Simi
 */
@SpringBootApplication
public class EventHubsApp implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(EventHubsApp.class);

    @Autowired
    private EventHubsService service;

    public static void main(String[] args) {
        SpringApplication.run(EventHubsApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(service);
    }

    @PostConstruct
    private void init() throws IOException {
    }
}
