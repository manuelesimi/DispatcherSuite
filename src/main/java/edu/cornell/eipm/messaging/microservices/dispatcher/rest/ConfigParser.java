package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.ConfiguredActions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

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
    private static ConfiguredActions config;

    /**
     * Parses the configuration from the YAML file.
     *
     * @throws IOException if the file is not found
     */
    static void parse() throws IOException {
        if (Objects.nonNull(config)) //config already loded
            return;
        try( InputStream ios = new FileInputStream(new File(CONFIG_FILE));) {
            config = new Yaml(new Constructor(ConfiguredActions.class)).load(ios);
        } catch (IOException e) {
            throw e;
        }
        if (Objects.isNull(config))
            throw new NullPointerException("Can't read/parse " + CONFIG_FILE);
    }

    /**
     * Gets the dispatcher configuration
     * @throws NullPointerException if {@code config} is {@code null}
     * @throws IOException if {@code config} is {@code invalid}
     */
    static ConfiguredActions getConfig() throws IOException {
        if (Objects.isNull(config))
            parse();
        return Objects.requireNonNull(config,"config is not available at this time");
    }

    /**
     * Returns a string representation of the configuration.
     * @return the serialized configuration
     */
    static String configToString() {
        return config.toString();
    }
}
