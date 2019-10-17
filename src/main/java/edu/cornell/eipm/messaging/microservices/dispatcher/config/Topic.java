package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;

/**
 * @author Manuele Simi
 */
public class Topic {

    private String topic;
    private List<Action> actions;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic='" + topic + '\'' +
                ", actions=" + actions +
                '}';
    }

    NewTopic toNewTopic() {
        return new NewTopic(this.topic, 3, (short) 1);
    }
}
