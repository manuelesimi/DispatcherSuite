package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;

public interface Executor {

    void execute(String trigger, StringPayload payload);
}
