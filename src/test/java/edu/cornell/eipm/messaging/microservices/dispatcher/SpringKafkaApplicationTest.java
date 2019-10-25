package edu.cornell.eipm.messaging.microservices.dispatcher;


import edu.cornell.eipm.messaging.microservices.dispatcher.broker.consumer.Receiver;
import edu.cornell.eipm.messaging.microservices.dispatcher.broker.producer.Sender;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1,
        topics = {SpringKafkaApplicationTest.TEST_TOPIC})
public class SpringKafkaApplicationTest {

    static final String TEST_TOPIC = "oncorseq_sequencing_in_progress";
    static  Map<String, String> params = new HashMap<>();

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @BeforeClass
    public static void setupParameters() {
        params.put("sampleID","sample123");
        params.put("runID","run123");
        params.put("sayHello","Hello from Spring Kafka Send/Receive!");
    }

    @Test
    public void testSendReceive() throws Exception {
        sender.send(TEST_TOPIC, params);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }

    @Test
    public void testSend() {
        sender.send(TEST_TOPIC,params);
    }

    @Test
    public void testReceive() throws Exception {
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
