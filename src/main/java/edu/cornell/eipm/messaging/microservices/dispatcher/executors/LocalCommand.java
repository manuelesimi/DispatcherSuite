package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * A command to execute on the local machine.
 *
 * @author Manuele Simi
 */
public class LocalCommand extends BaseExecutor {

    private final Logger logger = LoggerFactory.getLogger(LocalCommand.class);

    LocalCommand(Action action) {
        this.setAction(action);
    }

    @Override
    protected boolean run(String command) throws IOException {
        logger.info("Local execution for: {}", command);
        String ssh_command;
        String hostname = System.getenv("HOST_HOSTNAME");
        String hostuser = System.getenv("HOST_USER");
        if (hostname != null && !hostname.isEmpty() &&
                hostuser != null && !hostuser.isEmpty()) {
            // we are running inside a docker container
            ssh_command = String.format("ssh -t %s@%s '%s'",
                    hostuser,
                    hostname,
                    command);
            logger.info("Command wrapped as: {}", ssh_command);
        } else {
            // we go with a local execution
            ssh_command = command;
        }
        Process process = Runtime.getRuntime().exec(ssh_command);
        logger.info("Local Command Dispatched");
        return process.isAlive();
    }

}
