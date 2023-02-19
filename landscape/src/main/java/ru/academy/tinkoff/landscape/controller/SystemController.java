package ru.academy.tinkoff.landscape.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("system")
public class SystemController {
    /**
     * liveness status
     */
    @GetMapping("liveness")
    public void getLiveness() {
    }

    /**
     * @return Json with service name and readiness status
     */
    @GetMapping("readiness")
    public Map<String, String> getReadiness() {
        return Map.of("LandscapeService", "OK");
    }
}
