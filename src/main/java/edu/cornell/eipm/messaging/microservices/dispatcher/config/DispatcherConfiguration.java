package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuele Simi
 */
public class DispatcherConfiguration {

    private List<Topic> dispatcher;

    private Kafka kafka;

    public Kafka getKafka() {
        return kafka;
    }

    public void setKafka(Kafka kafka) {
        this.kafka = kafka;
    }


    public List<Topic> getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(List<Topic> dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Gets all registered dispatcher.
     * @return the names
     */
    public Set<String> getTopicNames() {
        return dispatcher.stream().map(Topic::getTopic).collect(Collectors.toSet());
    }

    /**
     * Gets the actions of interest for the given topic.
     * @param name the topic name
     * @return the list of messages
     */
    public List<Action> getActions(String name) {
        Optional<Topic> action = dispatcher.stream()
                .filter(t -> name.equals(t.getTopic()))
                .findAny();
        return action.orElse(new Topic()).getActions();
    }

    @Override
    public String toString() {
        return "DispatcherConfiguration{" +
                "dispatcher=" + dispatcher +
                '}';
    }


}
