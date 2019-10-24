package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Deserializer for map of values
 *
 * @author Manuele Simi
 */
public class JSONPayloadDeserializer {
    private final String data;

    /**
     * Creates the payload
     *
     * @param data the serialized payload
     */
    public JSONPayloadDeserializer(String data) {
        this.data = data;
    }

    public Map<String, String> fromJSON() {
        return new Gson().fromJson(data, Map.class);
    }

}
