package edu.cornell.eipm.messaging.microservices.eventhubs.dispatcher;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Manuele Simi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DispatcherApp.class)
@AutoConfigureMockMvc
class DispatcherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void publish() {
    }

    @Test
    public void aboutShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/about")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("EventHubs-Dispatcher Service 1.0"));
    }
}