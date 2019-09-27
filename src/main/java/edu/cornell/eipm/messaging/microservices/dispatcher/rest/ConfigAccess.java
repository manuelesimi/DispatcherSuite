package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.ConfiguredActions;
import edu.cornell.eipm.messaging.microservices.dispatcher.config.Message;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Access to the Dispatcher's configuration.
 * @author Manuele Simi
 */
public class ConfigAccess {

    protected static ConfiguredActions config;

    /**
     * Gets the dispatcher configuration
     * @throws NullPointerException if {@code config} is {@code null}
     * @throws IOException if {@code config} is {@code invalid}
     */
    static ConfiguredActions getConfig() throws IOException {
        if (Objects.isNull(config))
            ConfigParser.parse();
        return Objects.requireNonNull(config,"config is not available at this time");
    }

    /**
     * Gets all the topics of interest.
     * @return the names of the topics
     */
    protected static Set<String> getTopicNames() {
        return config.getTopicNames();
    }

    /**
     * Gets all the messages of interest associated to the topic.
     * @param name topic name
     * @return
     */
    protected static List<String> getMessages(String name) {
        return config.getMessages(name).stream().map(Message::toString).collect(Collectors.toList());
    }

    /**
     * Returns a string representation of the configuration.
     * @return the serialized configuration
     */
    static String configToString() {
        return config.toString();
    }
}
