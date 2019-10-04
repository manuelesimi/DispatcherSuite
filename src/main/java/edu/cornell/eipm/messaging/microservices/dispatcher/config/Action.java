package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import java.util.List;

/**
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
