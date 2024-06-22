package ru.academy.tinkoff.landscape.service;

import com.google.protobuf.Empty;
import io.grpc.StatusRuntimeException;
import lombok.Getter;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.academy.tinkoff.landscape.dto.ServiceStatusDTO;
import ru.academy.tinkoff.landscape.util.Pair;
import ru.academy.tinkoff.proto.ReadinessResponse;
import ru.academy.tinkoff.proto.StatusServiceGrpc;
import ru.academy.tinkoff.proto.VersionResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
@Setter
public class StatusService {
    @GrpcClient("HandymanService")
    private StatusServiceGrpc.StatusServiceBlockingStub handymanService;
    @GrpcClient("RancherService")
    private StatusServiceGrpc.StatusServiceBlockingStub rancherService;
    private String handymanServiceName = "HandymanService";
    private String rancherServiceName = "RancherService";

    /**
     * @return Map with service name and it's {@link ServiceStatusDTO}s
     */
    public Map<String, List<ServiceStatusDTO>> getStatuses() {
        Map<String, List<ServiceStatusDTO>> map = new HashMap<>(Map.of(handymanServiceName, new ArrayList<>(),
                rancherServiceName, new ArrayList<>()));
        for (var p : List.of(
                Pair.of(handymanServiceName, handymanService),
                Pair.of(rancherServiceName, rancherService)
        )) {
            try {
                ServiceStatusDTO dto = getServiceStatus(p.getSecond());
                map.get(p.getFirst()).add(dto);
            } catch (StatusRuntimeException ignored) {
                //
            }
        }
        return map;
    }

    private ServiceStatusDTO getServiceStatus(StatusServiceGrpc.StatusServiceBlockingStub stub) {
        VersionResponse versionResponse = stub.getVersion(Empty.getDefaultInstance());
        ReadinessResponse readinessResponse = stub.getReadiness(Empty.getDefaultInstance());
        return new ServiceStatusDTO(
                stub.getChannel().authority(),
                readinessResponse.getStatus(),
                versionResponse.getArtifact(),
                versionResponse.getName(),
                versionResponse.getGroup(),
                versionResponse.getVersion()
        );
    }

}
