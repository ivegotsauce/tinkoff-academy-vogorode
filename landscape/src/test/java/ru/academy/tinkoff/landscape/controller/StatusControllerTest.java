package ru.academy.tinkoff.landscape.controller;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.academy.tinkoff.landscape.service.StatusService;
import ru.academy.tinkoff.proto.ReadinessResponse;
import ru.academy.tinkoff.proto.StatusServiceGrpc;
import ru.academy.tinkoff.proto.VersionResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class StatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StatusService statusService;

    private final ReadinessResponse readiness = ReadinessResponse
            .newBuilder()
            .setStatus("IDLE")
            .build();
    private final VersionResponse rancherVersion = VersionResponse
            .newBuilder()
            .setArtifact("rancher")
            .setName("rancher")
            .setGroup("ru.academy.tinkoff")
            .setVersion("0.0.1-SNAPSHOT")
            .build();
    private final VersionResponse handymanVersion = VersionResponse
            .newBuilder()
            .setArtifact("handyman")
            .setName("handyman")
            .setGroup("ru.academy.tinkoff")
            .setVersion("0.0.1-SNAPSHOT")
            .build();

    private final ManagedChannel handymanChannel = ManagedChannelBuilder
            .forAddress("localhost", 9080)
            .build();

    private final ManagedChannel rancherChannel = ManagedChannelBuilder
            .forAddress("localhost", 9090)
            .build();

    // test if no connection to services
    @Test
    public void testNoStatuses() throws Exception {
        StatusServiceGrpc.StatusServiceBlockingStub handymanStub = mock(StatusServiceGrpc.StatusServiceBlockingStub.class);
        StatusServiceGrpc.StatusServiceBlockingStub rancherStub = mock(StatusServiceGrpc.StatusServiceBlockingStub.class);
        when(rancherStub.getVersion(Empty.getDefaultInstance()))
                .thenThrow(StatusRuntimeException.class);
        when(handymanStub.getVersion(Empty.getDefaultInstance()))
                .thenThrow(StatusRuntimeException.class);
        statusService.setRancherService(rancherStub);
        statusService.setHandymanService(handymanStub);
        var result = this.mockMvc.perform(get("/services/statuses"));
        result
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "RancherService": [],
                                "HandymanService": []
                            }
                        """));
    }

    @Test
    public void testGetStatuses() throws Exception {
        StatusServiceGrpc.StatusServiceBlockingStub handymanStub = mock(StatusServiceGrpc.StatusServiceBlockingStub.class);
        StatusServiceGrpc.StatusServiceBlockingStub rancherStub = mock(StatusServiceGrpc.StatusServiceBlockingStub.class);
        when(rancherStub.getVersion(Empty.getDefaultInstance()))
                .thenReturn(rancherVersion);
        when(handymanStub.getVersion(Empty.getDefaultInstance()))
                .thenReturn(handymanVersion);
        when(rancherStub.getReadiness(Empty.getDefaultInstance()))
                .thenReturn(readiness);
        when(handymanStub.getReadiness(Empty.getDefaultInstance()))
                .thenReturn(readiness);
        when(rancherStub.getChannel())
                .thenReturn(rancherChannel);
        when(handymanStub.getChannel())
                .thenReturn(handymanChannel);
        statusService.setRancherService(rancherStub);
        statusService.setHandymanService(handymanStub);
        var result = this.mockMvc.perform(get("/services/statuses"));
        result
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "RancherService": [
                                    {
                                        "host": "localhost:9090",
                                        "status": "IDLE",
                                        "artifact": "rancher",
                                        "name": "rancher",
                                        "group": "ru.academy.tinkoff",
                                        "version": "0.0.1-SNAPSHOT"
                                    }
                                ],
                                "HandymanService": [
                                    {
                                        "host": "localhost:9080",
                                        "status": "IDLE",
                                        "artifact": "handyman",
                                        "name": "handyman",
                                        "group": "ru.academy.tinkoff",
                                        "version": "0.0.1-SNAPSHOT"
                                    }
                                ]
                            }
                        """));
    }
}
