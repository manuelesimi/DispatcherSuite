package edu.cornell.eipm.messaging.microservices.dispatcher;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.DispatcherConfiguration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Parser for the DispatcherApp YAML configuration.
 *
 * @author Manuele Simi
 */
class ConfigParser {

    private static String CONFIG_FILE = "/Users/mas2182/WORK/EIPM/KafkaDispatcher/dispatcher-config.yml";

    /**
     * Parses the configuration from the YAML file.
     *
     * @throws IOException if the file is not found
     */
    static void parse() throws IOException {
        if (Objects.nonNull(ConfigAccess.config)) //config already loaded
            return;
        try( InputStream ios = new FileInputStream(new File(CONFIG_FILE));) {
            ConfigAccess.config = new Yaml(new Constructor(DispatcherConfiguration.class)).load(ios);
        } catch (IOException e) {
            throw e;
        }
        if (Objects.isNull(ConfigAccess.config))
            throw new NullPointerException("Can't read/parse " + CONFIG_FILE);
    }
}