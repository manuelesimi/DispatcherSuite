package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

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
public class Dispatcher {

    public static void main(String[] args){
        SpringApplication.run(Dispatcher.class, args);
    }

    @PostConstruct
    private void init() throws IOException {
        ConfigParser.parse();
    }
}
