package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    protected boolean run(String command, MODE mode) throws IOException {
        logger.info("Local execution for: {}", command);
        String ssh_command;
        String hostname = System.getenv("HOST_HOSTNAME");
        String hostuser = System.getenv("HOST_USER");
        if (hostname != null && !hostname.isEmpty() &&
                hostuser != null && !hostuser.isEmpty()) {
            // we are running inside a docker container
            ssh_command = String.format("ssh -o StrictHostKeyChecking=no -i /ssh/id_rsa -T -v %s@%s %s",
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
        if (mode == MODE.BLOCKING) {
            InputStream stdIn = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdIn);
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            logger.info("<OUTPUT>");

            while ((line = br.readLine()) != null)
                logger.info(line);

            logger.info("</OUTPUT>");

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            logger.info("<ERROR>");

            while ((line = stdError.readLine()) != null)
                logger.info(line);

            logger.info("</ERROR>");

            int exitVal = 0;
            try {
                exitVal = process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Process exit value: " + exitVal);
        } else {
            try {
                //wait few seconds befor to check if the process is alive
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("Unable to wait ",e);
            }
        }
        logger.debug("Process is alive? " + process.isAlive());
        logger.debug("Process exit value: " + process.exitValue());
        return (process.isAlive() || process.exitValue()==0);
    }

}
