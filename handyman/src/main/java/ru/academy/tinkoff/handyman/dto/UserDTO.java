package ru.academy.tinkoff.handyman.dto;

import java.util.List;

public record UserDTO(
        String firstName,
        String lastName,
        List<String> skills,
        String email,
        String phoneNumber,
        List<AccountDTO> accounts,
        byte[] photo
) {
}
