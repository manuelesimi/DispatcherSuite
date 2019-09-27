package edu.cornell.eipm.messaging.microservices.dispatcher.config;

/**
 * @author Manuele Simi
 */
public class Message {

    private String message;
    private String trigger;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", trigger='" + trigger + '\'' +
                '}';
    }
}
