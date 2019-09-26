package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

/**
 * Parser for the Dispatcher YAML configuration.
 *
 * @author Manuele Simi
 */
class ConfigParser {

    private static final String CONFIG_FILE = System.getProperty("dispatcher.config");
    private static Map<String, Object> config;

    /**
     * Parses the configuration from the YAML file.
     *
     * @throws IOException if the file is not found
     */
    static void parse() throws IOException {
        if (Objects.nonNull(config)) //config already loded
            return;
        try (InputStream inputStream = ConfigParser.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            Yaml yaml = new Yaml();
            config = yaml.load(inputStream);
        } catch (IOException e) {
            throw e;
        }
        if (Objects.isNull(config))
            throw new NullPointerException("Can't read/parse dispatcher-config.yml");
    }

    /**
     * Returns a string representation of the configuration.
     * @return
     */
    static String configToString() {
        return config.toString();
    }
}
