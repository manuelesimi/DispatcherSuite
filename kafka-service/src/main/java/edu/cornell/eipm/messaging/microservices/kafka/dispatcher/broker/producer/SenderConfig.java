package edu.cornell.eipm.messaging.microservices.kafka.dispatcher.broker.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration for the {@link Sender} bean.
 *
 * @author Manuele Simi
 */
@Configuration
public class SenderConfig {

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${kafka.properties.sasl.jaas.config}")
  private String jaasConfig;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put("sasl.mechanism", "PLAIN");
    props.put("security.protocol", "SASL_SSL");
    props.put("sasl.jaas.config", jaasConfig);

    return props;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public Sender sender() {
    return new Sender();
  }
}
