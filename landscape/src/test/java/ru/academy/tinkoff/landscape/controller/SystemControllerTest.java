package ru.academy.tinkoff.landscape.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.academy.tinkoff.landscape.component.RunAfterStartup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SystemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void livenessIsOk() throws Exception {
        // given
        String endpoint = "/system/liveness";
        // when
        var result = this.mockMvc.perform(get(endpoint));
        // then
        result.andExpect(status().isOk());
    }

    @Test
    public void readinessIsOk() throws Exception {
        // given
        String endpoint = "/system/readiness";
        // when
        var result = this.mockMvc.perform(get(endpoint));
        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.LandscapeService").value(RunAfterStartup.readiness));
    }
}
