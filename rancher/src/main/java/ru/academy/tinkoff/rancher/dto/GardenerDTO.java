package ru.academy.tinkoff.rancher.dto;

public record GardenerDTO(
        String firstName,
        String lastName,
        String login,
        FieldDTO[] fields,
        String email,
        String phoneNumber
) {
}
