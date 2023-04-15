package ru.academy.tinkoff.rancher.dto;

import java.util.List;

public record UserDTO(
        String login,
        String email,
        String phoneNumber,
        double square,
        double latitude,
        double longitude,
        List<String> works
) {
}