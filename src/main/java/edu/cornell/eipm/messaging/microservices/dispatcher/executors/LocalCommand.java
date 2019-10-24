package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalCommand implements Executor {

    private final Logger logger = LoggerFactory.getLogger(LocalCommand.class);
    private Action action;

    LocalCommand(Action action) {
        this.setAction(action);
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public void execute(StringPayload payload) {
        logger.info("Local execution for: {}", action.getTrigger() );
    }
}
