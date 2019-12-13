package edu.cornell.eipm.messaging.microservices.executors.runtime;

import edu.cornell.eipm.messaging.microservices.executors.model.service.Action;

import java.io.IOException;
import java.util.Map;

/**
 * Interface for all Executors.
 * @author Manuele Simi
 */
public interface Executor {

    void setAction(Action action);
    boolean execute( Map<String,String> values, MODE mode) throws IOException;
}
