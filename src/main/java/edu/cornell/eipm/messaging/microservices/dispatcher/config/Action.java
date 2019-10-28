package edu.cornell.eipm.messaging.microservices.dispatcher.config;


/**
 * An action associated to the {@link Topic}
 * @author Manuele Simi
 */
public class Action {

    private String trigger;
    private String workingDir;
    private Reply reply;

    public String getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
