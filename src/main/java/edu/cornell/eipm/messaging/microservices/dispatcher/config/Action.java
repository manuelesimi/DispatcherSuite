package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import java.util.List;

/**
 * @author Manuele Simi
 */
public class Action {

    private String topic;
    private List<Message> messages;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Action{" +
                "topic='" + topic + '\'' +
                ", messages=" + messages +
                '}';
    }
}
