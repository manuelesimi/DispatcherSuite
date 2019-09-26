package edu.cornell.eipm.messaging.microservices.dispatcher.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dispatcher.class)
@AutoConfigureMockMvc
public class DispatcherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamAboutShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/dispatcher/about")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Dispatcher 1.0 says: hello client"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/dispatcher/about").param("name", "EIPM"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Dispatcher 1.0 says: hello EIPM"));
    }

}
