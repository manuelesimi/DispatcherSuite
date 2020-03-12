package edu.cornell.eipm.messaging.microservices.executors.runtime;

import edu.cornell.eipm.messaging.microservices.executors.model.service.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermissions;
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
        Path tmpFile = Files.createTempFile("kd", ".sh",
                PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwx------")));
        if (hostname != null && !hostname.isEmpty() &&
                hostuser != null && !hostuser.isEmpty()) {
            // we are running inside a docker container
            ssh_command = String.format("ssh -o StrictHostKeyChecking=no -i /ssh/id_rsa -T -v %s@%s %s",
                    hostuser,
                    hostname,
                    command);
            logger.info("SSH command wrapped as: {}", ssh_command);
        } else {
            // we go with a local execution
            String wrappedCommand = "#!/usr/bin/env sh \n";
            wrappedCommand += command + "\n";
            byte[] strToBytes = wrappedCommand.getBytes();
            Files.write(tmpFile, strToBytes);
            ssh_command = tmpFile.toAbsolutePath().toString();
            logger.info("Local command wrapped as: {}", ssh_command);
        }
        Process process = Runtime.getRuntime().exec(ssh_command);
        logger.info("Local Command Dispatched");
        if (mode == MODE.BLOCKING) {
            InputStream stdIn = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdIn);
            BufferedReader br = new BufferedReader(isr);

            String line = null;

            //catch the std output
            logger.info("<OUTPUT>");
            while ((line = br.readLine()) != null)
                logger.info(line);
            logger.info("</OUTPUT>");
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            //catch the std error
            logger.info("<ERROR>");
            while ((line = stdError.readLine()) != null)
                logger.info(line);
            logger.info("</ERROR>");

            //check if the process is done
            int exitVal = 0;
            try {
                exitVal = process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Process exit value: " + exitVal);
        } else {
            try {
                //wait few seconds before to check if the process is alive
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("Unable to wait ",e);
            }
        }
        logger.debug("Process is alive? " + process.isAlive());
        //calling exitValue if alive results in a IllegalThreadStateException.
        if (Objects.nonNull(tmpFile))
            tmpFile.toFile().delete();
        return (process.isAlive() || process.exitValue()==0);
    }

}
