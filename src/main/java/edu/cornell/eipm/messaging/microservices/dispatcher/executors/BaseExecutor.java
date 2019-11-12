package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;

import java.io.IOException;
import java.util.Map;

/**
 * Base logic commond to all {@link Executor}s
 * @author Manuele Simi
 */
public abstract class BaseExecutor implements Executor {

    protected Action action;

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean execute(Map<String, String> values) throws IOException {
        String actualTrigger = new TriggerValue(action.getTrigger()).getActualValue(values);
        return this.run(actualTrigger);
    }

    /**
     * Runs the trigger with its actual values
     * @param actualTrigger
     * @return
     * @throws IOException
     */
    protected abstract boolean run(String actualTrigger) throws IOException;

}
