package edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher.config;

import edu.cornell.eipm.messaging.microservices.executors.model.service.Service;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Root configuration for the EventHubs service.
 *
 * @author Manuele Simi
 */
@Component
@Validated
@Configuration
@ConfigurationProperties("dispatcher")
public class EventHubsService {

    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
