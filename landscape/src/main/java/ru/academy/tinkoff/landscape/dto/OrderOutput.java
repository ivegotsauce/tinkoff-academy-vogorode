package ru.academy.tinkoff.landscape.dto;

import ru.academy.tinkoff.landscape.handyman.User;
import ru.academy.tinkoff.landscape.rancher.FieldProjection;

import java.util.Date;

public record OrderOutput(
        Long id,
        FieldProjection garden,
        User user,
        String workType,
        String status,
        Date creationDate
) {
}
