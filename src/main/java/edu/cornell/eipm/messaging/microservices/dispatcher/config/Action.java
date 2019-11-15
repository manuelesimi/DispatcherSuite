package edu.cornell.eipm.messaging.microservices.dispatcher.config;


/**
 * An action associated to the {@link Topic}
 * @author Manuele Simi
 */
public class Action {

    private String trigger;
    private Reply reply;

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
