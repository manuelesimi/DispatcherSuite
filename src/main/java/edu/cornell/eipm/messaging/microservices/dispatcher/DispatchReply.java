package edu.cornell.eipm.messaging.microservices.dispatcher;

import java.util.ArrayList;
import java.util.List;

public class DispatchReply {


    private List<String> triggers = new ArrayList<>();
    private List<String> replies = new ArrayList<>();

    public void addTrigger(String trigger) {
        this.triggers.add(trigger);
    }

    public void addReply(String topic, String replace) {
        this.replies.add("Publish to " + topic + " with payload " + replace);
    }

    public List<String> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<String> triggers) {
        this.triggers = triggers;
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }

}
