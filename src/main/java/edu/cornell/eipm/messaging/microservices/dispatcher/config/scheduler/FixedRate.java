package edu.cornell.eipm.messaging.microservices.dispatcher.config.scheduler;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;

import java.util.ArrayList;
import java.util.List;

public class FixedRate {

    long rate;
    private List<Action> actions = new ArrayList<>();


    public List<Action> getActions() {
        return actions;
    }
}
