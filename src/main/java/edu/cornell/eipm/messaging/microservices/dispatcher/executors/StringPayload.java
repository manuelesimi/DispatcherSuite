package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import java.util.List;
import java.util.Map;

/**
 * A String payload.
 *
 * @author Manuele Simi
 */
public class StringPayload {

    private  final Map<String, String> parameters;

    /**
     * Creates the payload
     * @param payload the serialized payload
     */
    public StringPayload(String payload) {
        parameters = this.parse(payload);
    }

    public Map<String, String> parse(String payload) {

        return null;
    }
}
