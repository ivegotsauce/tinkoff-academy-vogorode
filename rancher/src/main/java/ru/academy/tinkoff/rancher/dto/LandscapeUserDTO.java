package ru.academy.tinkoff.rancher.dto;

import java.io.Serializable;

public record LandscapeUserDTO(
        String userType,
        String login,
        String email,
        String phoneNumber,
        double latitude,
        double longitude
) implements Serializable {
}