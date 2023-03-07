package ru.academy.tinkoff.rancher.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.academy.tinkoff.rancher.component.RunAfterStartup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SystemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String endpoint;
    private ResultActions response;

    private void givenEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    private void whenDoGetEndpoint() throws Exception {
        this.response = this.mockMvc.perform(get(endpoint));
    }

    private void thenResponseIs200() throws Exception {
        this.response.andExpect(status().isOk());
    }

    private void thenResponseIs200AndOk() throws Exception {
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.RancherService").value(RunAfterStartup.readiness));
    }

    @Test
    public void livenessIsOk() throws Exception {
        givenEndpoint("/system/liveness");
        whenDoGetEndpoint();
        thenResponseIs200();
    }

    @Test
    public void readinessIs200AndOk() throws Exception {
        givenEndpoint("/system/readiness");
        whenDoGetEndpoint();
        thenResponseIs200AndOk();
    }
}
