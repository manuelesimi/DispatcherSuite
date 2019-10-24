package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Calls a remote service
 *
 * @author Manuele Simi
 */
public class RemoteCall implements Executor {

    private Action action;
    private final Logger logger = LoggerFactory.getLogger(RemoteCall.class);

    public RemoteCall(Action action) {
        this.setAction(action);
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public void execute(Map<String, String> values) {
        logger.info("Remote call to: {}", action.getTrigger() );
        logger.info("with params: " + values.toString());
    }

}
