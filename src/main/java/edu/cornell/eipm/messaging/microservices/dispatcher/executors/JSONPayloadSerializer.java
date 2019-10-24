package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        Gson prettyGson = new  GsonBuilder().setPrettyPrinting().create();
        return  prettyGson.toJson(data);
    }
}
