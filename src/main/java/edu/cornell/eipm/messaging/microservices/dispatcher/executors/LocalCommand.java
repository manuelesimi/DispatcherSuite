package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
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
        logger.info("Local execution for: {}", command );
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        if (Objects.nonNull(action.getWorkingDir()))
            processBuilder.directory(new File(action.getWorkingDir()));
        Process process = processBuilder.start();
        logger.info("Local Command Dispatched");
        return process.isAlive();
    }

}
