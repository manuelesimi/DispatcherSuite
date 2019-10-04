package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import edu.cornell.eipm.messaging.microservices.dispatcher.config.DispatcherConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Access to the DispatcherApp's configuration.
 *
 * @author Manuele Simi
 */
class ConfigAccess {

    protected static DispatcherConfiguration config;

    /**
     * Gets the dispatcher configuration
     *
     * @throws NullPointerException if {@code config} is {@code null}
     * @throws IOException          if {@code config} is {@code invalid}
     */
    static DispatcherConfiguration getConfig() {
        try {
            ConfigParser.parse();
        } catch (IOException e) {
            throw new NullPointerException("Unable to parse/access the config");
        }

        return Objects.requireNonNull(config, "config is not available at this time");
    }

    /**
     * Gets all the topics of interest.
     *
     * @return the names of the topics
     */
    protected static Set<String> getTopicNames() {
        return getConfig().getTopicNames();
    }


    /**
     * Returns a string representation of the configuration.
     *
     * @return the serialized configuration
     */
    static String configToString() {
        return config.toString();
    }

    /**
     * Gets the action(s) associated to the topic.
     *
     * @param topic the topic name
     * @return
     */
    protected static List<Action> getActions(String topic) {
        return getConfig().getActions(topic);
        /*Optional<Action> found = getConfig().getActions(topic).stream()
                .filter(m -> m.ge().equalsIgnoreCase(message))
                .findAny();
        return actions.map(value -> actions.getTrigger().replace("${payload}", payload)).orElse("");*/
    }

    @Override
    public String toString() {
        return "ConfigAccess{" +
                config.toString() +
                "}";
    }
}
