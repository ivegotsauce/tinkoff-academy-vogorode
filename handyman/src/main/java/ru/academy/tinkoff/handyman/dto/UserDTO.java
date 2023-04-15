package ru.academy.tinkoff.handyman.dto;

import java.util.List;

public record UserDTO(
        String login,
        String email,
        String phoneNumber,
        double latitude,
        double longitude,
        List<String> services
) {
}
