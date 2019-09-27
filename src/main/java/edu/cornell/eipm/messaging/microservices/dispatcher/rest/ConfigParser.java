package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
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

    private static String CONFIG_FILE = "/Users/manuelesimi/Work/EIPM/Projects/Dispatcher/dispatcher-config.yml";
    private static Map<String, Object> config;

    /**
     * Parses the configuration from the YAML file.
     *
     * @throws IOException if the file is not found
     */
    static void parse() throws IOException {
        if (Objects.nonNull(config)) //config already loded
            return;
        try( InputStream ios = new FileInputStream(new File(CONFIG_FILE));) {
            config = new Yaml().load(ios);
        } catch (IOException e) {
            throw e;
        }
        if (Objects.isNull(config))
            throw new NullPointerException("Can't read/parse dispatcher-config.yml");
    }

    /**
     * Returns a string representation of the configuration.
     * @return the serialized configuration
     */
    static String configToString() {
        return config.toString();
    }
}
