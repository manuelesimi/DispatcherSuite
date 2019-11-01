package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * A topic with registered actions to trigger
 * @author Manuele Simi
 */
public class Topic {


    private String name;
    private List<Action> actions = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", actions=" + actions +
                '}';
    }

    NewTopic toNewTopic() {
        return new NewTopic(this.name, 3, (short) 1);
    }
}
