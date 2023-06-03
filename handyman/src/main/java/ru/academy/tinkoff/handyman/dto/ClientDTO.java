package ru.academy.tinkoff.handyman.dto;

import java.util.List;

public record ClientDTO(
        String login,
        String email,
        String phoneNumber,
        double latitude,
        double longitude,
        List<String> services
) {
}
