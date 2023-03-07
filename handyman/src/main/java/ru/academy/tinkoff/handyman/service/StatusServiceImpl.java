package ru.academy.tinkoff.handyman.service;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import ru.academy.tinkoff.proto.ReadinessResponse;
import ru.academy.tinkoff.proto.StatusServiceGrpc;
import ru.academy.tinkoff.proto.VersionResponse;

import java.util.Objects;

@GrpcService
@RequiredArgsConstructor
public class StatusServiceImpl extends StatusServiceGrpc.StatusServiceImplBase {
    private final BuildProperties buildProperties;
    private final Environment environment;

    /**
     * Provides version of the service with gRPC.
     * @param request body of request
     * @param responseObserver sends service response
     */
    @Override
    public void getVersion(Empty request, StreamObserver<VersionResponse> responseObserver) {
        VersionResponse response = VersionResponse.newBuilder()
                .setArtifact(buildProperties.getArtifact())
                .setName(buildProperties.getName())
                .setGroup(buildProperties.getGroup())
                .setVersion(buildProperties.getVersion())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**
     * Provides readiness status of the service with gRPC.
     * @param request body of request
     * @param responseObserver sends service response
     */
    @Override
    public void getReadiness(Empty request, StreamObserver<ReadinessResponse> responseObserver) {
        String status = ManagedChannelBuilder
                .forAddress(environment.getProperty("grpc.server.address"),
                        Integer.parseInt(Objects.requireNonNull(environment.getProperty("grpc.server.port"))))
                .usePlaintext()
                .build()
                .getState(true)
                .toString();
        ReadinessResponse response = ReadinessResponse.newBuilder()
                .setStatus(status)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
