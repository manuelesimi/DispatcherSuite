package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;

/**
 * @author Manuele Simi
 */
public class ExecutorService {

    /**
     * Selects the appropriate executor according to the action
     * @param action
     * @return
     */
    public static Executor select(Action action) {
        return new LocalCommand(action);
    }
}
