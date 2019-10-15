package edu.cornell.eipm.messaging.microservices.dispatcher.kafka;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.concurrent.TimeUnit;

import edu.cornell.eipm.messaging.microservices.dispatcher.rest.DispatcherApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Manuele Simi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DispatcherApp.class)
@DirtiesContext
@EmbeddedKafka(partitions = 1,
        topics = {SpringKafkaApplicationTest.HELLOWORLD_TOPIC})
public class SpringKafkaApplicationTest {

    static final String HELLOWORLD_TOPIC = "A.t";

    @Autowired
    private DConsumer consumer;

    @Autowired
    private DProducer producer;

    @Test
    public void testReceive() throws Exception {
        producer.send(HELLOWORLD_TOPIC,"Hello Spring Kafka!");

        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(consumer.getLatch().getCount()).isEqualTo(0);
    }
}
