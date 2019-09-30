package edu.cornell.eipm.messaging.microservices.dispatcher.config;

/**
 * @author Manuele Simi
 */
public class Kafka {

    private String broker;
    private String groupId;


    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
