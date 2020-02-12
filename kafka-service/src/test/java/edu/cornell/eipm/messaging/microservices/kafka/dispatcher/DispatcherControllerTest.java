package edu.cornell.eipm.messaging.microservices.kafka.dispatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DispatcherApp.class)
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1,
        topics = {SpringKafkaApplicationTest.TEST_TOPIC})
public class DispatcherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamAboutShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/about")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Kafka-Dispatcher Service 1.1"));
    }

    @Test
    public void configuration() throws Exception {
        this.mockMvc.perform(get("/configuration"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void publishData() throws Exception {
        this.mockMvc.perform(post("/publish-data/"+SpringKafkaApplicationTest.TEST_TOPIC)
                .content("{\"runID\":\"xyz\",\"sampleID\":\"1234\"}")
                .contentType(MediaType.TEXT_PLAIN_VALUE))
                .andDo(print()).andExpect(status().isOk());
    }

}
