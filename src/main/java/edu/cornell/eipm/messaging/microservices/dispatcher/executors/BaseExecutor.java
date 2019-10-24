package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;

import java.util.Map;

public abstract class BaseExecutor implements Executor {

    protected Action action;

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    protected String replace(Map<String, String> values) {
        final String[] trigger = {action.getTrigger()};
        values.forEach((k, v) -> trigger[0] = trigger[0].replace("${"+ k +"}", v));
        return trigger[0];
    }

}
