package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuele Simi
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "dispatcher")
public class TopicConfigurations {

    private List<Topic> topics;



    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    /**
     * Gets all registered topics.
     * @return the names
     */
    public Set<String> getTopicNames() {
        return topics.stream().map(Topic::getTopic).collect(Collectors.toSet());
    }


    public Optional<List<Topic>> getTopics() {
        return Optional.ofNullable(topics);
    }
    /**
     * Gets the actions of interest for the given topic.
     * @param name the topic name
     * @return the list of messages
     */
    public List<Action> getActions(String name) {
        Optional<Topic> action = topics.stream()
                .filter(t -> name.equals(t.getTopic()))
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
