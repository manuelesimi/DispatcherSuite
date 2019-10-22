package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuele Simi
 */
@Component
@Validated
@Configuration
@ConfigurationProperties("dispatcher")
public class TopicConfigurations {

    private List<Topic> topics = new ArrayList<>();

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Optional<List<Topic>> getTopics() {
        return Optional.ofNullable(topics);
    }

    /**
     * Gets all registered topics.
     * @return the names
     */
    public Set<String> getTopicNames() {
        return topics.stream().map(Topic::getName).collect(Collectors.toSet());
    }


    /**
     * Gets the actions of interest for the given topic.
     * @param name the topic name
     * @return the list of messages
     */
    public List<Action> getActions(String name) {
        Optional<Topic> action = topics.stream()
                .filter(t -> name.equals(t.getName()))
                .findAny();
        return action.orElse(new Topic()).getActions();
    }

    @Override
    public String toString() {
        return "TopicConfigurations{" +
                "topics=" + topics +
                '}';
    }


}
