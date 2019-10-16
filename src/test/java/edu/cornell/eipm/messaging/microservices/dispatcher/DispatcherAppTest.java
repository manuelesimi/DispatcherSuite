package edu.cornell.eipm.messaging.microservices.dispatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.cornell.eipm.messaging.microservices.dispatcher.DispatcherApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DispatcherApp.class)
@AutoConfigureMockMvc
public class DispatcherAppTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamAboutShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/about")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("DispatcherApp 1.0 says: hello client"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
        this.mockMvc.perform(get("/about").param("name", "EIPM"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("DispatcherApp 1.0 says: hello EIPM"));
    }

    @Test
    public void configuration() throws Exception {
        this.mockMvc.perform(get("/configuration"))
                .andDo(print()).andExpect(status().isOk());
    }

}
