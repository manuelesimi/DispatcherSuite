package edu.cornell.eipm.messaging.microservices.dispatcher;


import edu.cornell.eipm.messaging.microservices.dispatcher.broker.consumer.Receiver;
import edu.cornell.eipm.messaging.microservices.dispatcher.broker.producer.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1,
    topics = {SpringKafkaApplicationTest.HELLOWORLD_TOPIC})
public class SpringKafkaApplicationTest {

  static final String HELLOWORLD_TOPIC = "topic1.t";

  @Autowired
  private Receiver receiver;

  @Autowired
  private Sender sender;

  //@Test
  public void testSendReceive() throws Exception {
    sender.send(HELLOWORLD_TOPIC,"Hello from Spring Kafka Send/Receive!");

    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
  }


  @Test
  public void testSend() throws Exception {
    sender.send(HELLOWORLD_TOPIC,"Hello from Spring Kafka Send!");
  }

  @Test
  public void testReceive() throws Exception {
    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
  }
}
