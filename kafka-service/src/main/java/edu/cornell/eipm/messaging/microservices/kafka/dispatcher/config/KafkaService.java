package edu.cornell.eipm.messaging.microservices.kafka.dispatcher.config;

import edu.cornell.eipm.messaging.microservices.executors.model.scheduler.Scheduler;
import edu.cornell.eipm.messaging.microservices.executors.model.service.Service;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author Manuele Simi
 */
@Component
@Validated
@Configuration
@ConfigurationProperties("dispatcher")
public class KafkaService {

    private Service service;

    private Scheduler scheduler;

    public Service getService() {
        return service;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
