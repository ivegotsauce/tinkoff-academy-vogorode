package ru.academy.tinkoff.handyman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.academy.tinkoff.handyman.component.RunAfterStartup;

import java.util.Map;

@RestController
@RequestMapping("system")
public class SystemController {

    /**
     * liveness status
     */
    @GetMapping("liveness")
    public ResponseEntity<HttpStatus> getLiveness() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @return Json with service name and readiness status
     */
    @GetMapping("readiness")
    public Map<String, String> getReadiness() {
        return Map.of("HandymanService", RunAfterStartup.readiness);
    }
}
