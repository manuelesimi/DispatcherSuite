package edu.cornell.eipm.messaging.microservices.executors.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Build map of values from the configured payload of a {@link edu.cornell.eipm.messaging.microservices.dispatcher.config.Reply}
 *
 * @author Manuele Simi
 */
public class ReplyPayloadParser {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReplyPayloadParser.class);

    /**
     * Parses the payload and creates the map ov values for the message payload
     * @param payload
     * @param requestValues
     * @return
     */
    public static Map<String, String> parse(String payload, final Map<String, String> requestValues) {

        Map<String, String> values = new HashMap<>();
        String[] params = payload.split("&");
        for (String param:params) {
            String[] entry = param.split("=");
            if (entry.length == 2) {
                values.put(entry[0],new TriggerValue(entry[1]).getActualValue(requestValues));
            } else {
                LOGGER.error("Invalid payload format at " + param + ". Missing = between name and value");
                throw new IllegalArgumentException("Invalid payload format at " + param + ". Missing = between name and value");
            }
        }
        return values;
    }
}
