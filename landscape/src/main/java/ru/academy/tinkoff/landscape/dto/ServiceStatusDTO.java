package ru.academy.tinkoff.landscape.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceStatusDTO {
    private String host;
    private String status;
    private String artifact;
    private String name;
    private String group;
    private String version;
}
