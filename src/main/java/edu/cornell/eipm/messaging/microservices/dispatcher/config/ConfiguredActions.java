package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuele Simi
 */
public class ConfiguredActions {

    private List<Action> actions;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Set<String> getTopics() {
        return actions.stream().map(Action::getTopic).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "ConfiguredActions{" +
                "actions=" + actions +
                '}';
    }
}
