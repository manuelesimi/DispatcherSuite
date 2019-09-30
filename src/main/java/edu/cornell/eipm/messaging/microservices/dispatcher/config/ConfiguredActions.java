package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuele Simi
 */
public class ConfiguredActions {

    private List<Action> actions;

    private Kafka kafka;

    public Kafka getKafka() {
        return kafka;
    }

    public void setKafka(Kafka kafka) {
        this.kafka = kafka;
    }


    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    /**
     * Gets all registered topics.
     * @return the names
     */
    public Set<String> getTopicNames() {
        return actions.stream().map(Action::getTopic).collect(Collectors.toSet());
    }

    /**
     * Gets the messages of interest for the given topic.
     * @param topic the topic name
     * @return the list of messages
     */
    public List<Message> getMessages(String topic) {
        Optional<Action> action = actions.stream()
                .filter(t -> topic.equals(t.getTopic()))
                .findAny();
        return action.orElse(new Action()).getMessages();
    }

    @Override
    public String toString() {
        return "ConfiguredActions{" +
                "actions=" + actions +
                '}';
    }


}
