package ru.academy.tinkoff.landscape.dto;

public record OrderDTO(
        Long gardenId,
        Long userId,
        String workType,
        String workStatus
) {
}
