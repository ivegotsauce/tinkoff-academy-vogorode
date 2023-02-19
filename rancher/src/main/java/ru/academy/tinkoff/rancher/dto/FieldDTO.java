package ru.academy.tinkoff.rancher.dto;

import org.locationtech.jts.geom.Coordinate;

import java.util.List;

public record FieldDTO(
        String address,
        double latitude,
        double longitude,
        Coordinate[] coordinates
) {
}
