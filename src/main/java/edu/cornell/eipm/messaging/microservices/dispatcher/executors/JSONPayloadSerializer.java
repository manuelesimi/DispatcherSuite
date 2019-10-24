package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Serializer for map of values
 *
 * @author Manuele Simi
 */
public class JSONPayloadSerializer {

    private final Map<String, String> data;

    /**
     * Creates the payload
     *
     * @param data the serialized payload
     */
    public JSONPayloadSerializer(Map<String, String> data) {
        this.data = data;
    }

    public String toJSON() {
        return new Gson().toJson(data);
    }
}
