package ru.academy.tinkoff.landscape.component;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {

    public static String readiness = "Not ready";

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        readiness = "OK";
    }
}
