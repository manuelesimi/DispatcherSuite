package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
