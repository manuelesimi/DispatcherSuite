package edu.cornell.eipm.messaging.microservices.kafka.dispatcher;

public class About {

    private String component = "DispatcherApp";
    private String version = "1.0";
    final private String name;
    private final String content;


    public About(String name) {
        this.name = name;
        this.content = String.format("%s %s says: hello %s", component, version, name);
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
