package ru.academy.tinkoff.landscape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.academy.tinkoff.landscape.dto.ServiceStatusDTO;
import ru.academy.tinkoff.landscape.service.StatusService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("services")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    /**
     * Get statuses of services, defined in application's configuration file.
     *
     * @return Map with service name and it's {@link ServiceStatusDTO}s
     */
    @GetMapping("statuses")
    public Map<String, List<ServiceStatusDTO>> getStatuses() {
        return statusService.getStatuses();
    }
}
