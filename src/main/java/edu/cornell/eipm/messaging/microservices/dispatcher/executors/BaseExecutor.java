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

    protected String replace(Map<String, String> values) {
        final String[] trigger = {action.getTrigger()};
        values.forEach((k, v) -> trigger[0] = trigger[0].replace("${"+ k +"}", validate(v)));
        return trigger[0];
    }

    @Override
    public void execute(Map<String, String> values) throws IOException {
        String actualTrigger = this.replace(values);
        this.run(actualTrigger);
    }

    /**
     * Looks for maliciuos parameters
     * @param value
     * @return
     */
    private String validate(String value) {
        if (value.contains(" "))
            throw new IllegalArgumentException("Parameters with space(s) are not accepted");
        if (value.toLowerCase().contains("bash"))
            throw new IllegalArgumentException("Looks like the parameter is trying to execute a script");
        return value;
    }

    /**
     * Runs the trigger with its actual values
     * @param actualTrigger
     * @return
     * @throws IOException
     */
    protected abstract boolean run(String actualTrigger) throws IOException;

}
