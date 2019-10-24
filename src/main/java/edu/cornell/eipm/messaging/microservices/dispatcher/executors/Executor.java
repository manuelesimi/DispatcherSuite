package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;

/**
 * Inteface for all Executors.
 * @author Manuele Simi
 */
public interface Executor {

    void setAction(Action action);
    void execute( StringPayload payload);
}
