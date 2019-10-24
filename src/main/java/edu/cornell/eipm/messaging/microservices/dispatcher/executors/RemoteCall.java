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
public class RemoteCall extends BaseExecutor {

    private final Logger logger = LoggerFactory.getLogger(RemoteCall.class);

    public RemoteCall(Action action) {
        this.setAction(action);
    }

    @Override
    public void execute(Map<String, String> values) {
        logger.info("Remote call to: {}", this.replace(values)  );
    }

}
