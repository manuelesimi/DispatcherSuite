package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Creates the topics of interest if requested.
 *
 * @author Manuele Simi
 */
@Configuration
public class TopicAdministrator {
    private final TopicConfigurations configurations;
    private final GenericWebApplicationContext context;

    public TopicAdministrator(TopicConfigurations configurations,	GenericWebApplicationContext genericContext) {
        this.configurations = configurations;
        this.context = genericContext;
    }

    @PostConstruct
    public void createTopics() {
        configurations
                .getTopics()
                .ifPresent(this::initializeBeans);
    }

    private void initializeBeans(List<Topic> topics) {
        topics.forEach(t -> {
            context.registerBean(t.getTopic(), NewTopic.class, t::toNewTopic);
        });
    }
}