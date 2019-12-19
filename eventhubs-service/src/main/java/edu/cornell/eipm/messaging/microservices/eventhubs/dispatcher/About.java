package edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher;

public class About {

    private String component = "EventHubs-Dispatcher Service";
    private String version = "1.0";
    private final String content;


    public About() {
        this.content = String.format("%s %s", component, version);
    }

    public String getComponent() {
        return component;
    }

    public String getVersion() {
        return version;
    }

    public String getContent() {
        return content;
    }
}