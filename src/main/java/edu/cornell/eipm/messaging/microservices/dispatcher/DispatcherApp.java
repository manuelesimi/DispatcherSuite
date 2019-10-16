package edu.cornell.eipm.messaging.microservices.dispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
