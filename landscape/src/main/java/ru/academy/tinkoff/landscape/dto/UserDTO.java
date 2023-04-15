package ru.academy.tinkoff.landscape.dto;

public record UserDTO(
        String userType,
        String login,
        String email,
        String phoneNumber,
        double latitude,
        double longitude
) {
}
