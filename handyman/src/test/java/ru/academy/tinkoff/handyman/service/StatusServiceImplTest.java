package ru.academy.tinkoff.handyman.service;

import com.google.protobuf.Empty;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.academy.tinkoff.proto.ReadinessResponse;
import ru.academy.tinkoff.proto.StatusServiceGrpc;
import ru.academy.tinkoff.proto.VersionResponse;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "grpc.server.inProcessName=test", // Enable inProcess server
        "grpc.server.port=-1", // Disable external server
        "grpc.client.inProcess.address=in-process:test" // Configure the client to connect to the inProcess server
})
@DirtiesContext
public class StatusServiceImplTest {
    @GrpcClient("inProcess")
    private StatusServiceGrpc.StatusServiceBlockingStub stub;
    @Autowired
    private BuildProperties buildProperties;

    @Test
    @DirtiesContext
    public void testGetVersion() {
        VersionResponse response = stub.getVersion(Empty.getDefaultInstance());
        VersionResponse expected = VersionResponse.newBuilder()
                .setArtifact(buildProperties.getArtifact())
                .setVersion(buildProperties.getVersion())
                .setGroup(buildProperties.getGroup())
                .setName(buildProperties.getName())
                .build();
        assertEquals(expected, response);
    }

    @Test
    @DirtiesContext
    public void testGetReadiness() {
        ReadinessResponse response = stub.getReadiness(Empty.getDefaultInstance());
        ReadinessResponse expected = ReadinessResponse.newBuilder()
                .setStatus("IDLE")
                .build();
        assertEquals(expected, response);
    }
}
