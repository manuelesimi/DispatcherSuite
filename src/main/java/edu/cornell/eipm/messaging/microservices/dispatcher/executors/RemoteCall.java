package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Calls a remote service
 *
 * @author Manuele Simi
 */
public class RemoteCall implements Executor {

    private final Logger logger = LoggerFactory.getLogger(RemoteCall.class);

    @Override
    public void execute(String trigger, StringPayload payload) {
        logger.info("Remote call to: {}", trigger );
    }
}
