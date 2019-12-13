package edu.cornell.eipm.messaging.microservices.executors.runtime;

import edu.cornell.eipm.messaging.microservices.executors.model.service.Action;

/**
 * @author Manuele Simi
 */
public class ExecutorService {

    /**
     * Selects the appropriate executor for the action
     * @param action
     * @return
     */
    public static Executor select(Action action) {
        String trigger = action.getTrigger().toUpperCase();
        if (trigger.startsWith("HTTP"))
            return new RemoteCall(action);
        return new LocalCommand(action);
    }
}
