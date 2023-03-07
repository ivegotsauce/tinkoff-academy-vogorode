package ru.academy.tinkoff.landscape.dto;

public record ServiceStatusDTO(String host, String status, String artifact, String name, String group, String version) {
}
