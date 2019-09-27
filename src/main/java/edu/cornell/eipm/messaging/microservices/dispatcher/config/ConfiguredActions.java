package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import java.util.List;

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

    @Override
    public String toString() {
        return "ConfiguredActions{" +
                "actions=" + actions +
                '}';
    }
}
