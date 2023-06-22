package ru.academy.tinkoff.rancher.util;

import ru.academy.tinkoff.rancher.domain.Field;
import ru.academy.tinkoff.rancher.domain.Gardener;
import ru.academy.tinkoff.rancher.dto.FieldProjection;
import ru.academy.tinkoff.rancher.dto.GardenerOutput;

public class Util {
    public static FieldProjection fieldToProjection(Field field) {
        FieldProjection projection = new FieldProjection();
        projection.setAddress(field.getAddress());
        projection.setLongitude(field.getLongitude());
        projection.setLatitude(field.getLatitude());
        projection.setArea(field.getArea().getArea());
        projection.setGardener(field.getGardener());
        projection.setId(field.getId());
        return projection;
    }

    public static GardenerOutput gardenerToOutput(Gardener gardener) {
        GardenerOutput output = new GardenerOutput();
        output.setId(gardener.getId());
        output.setEmail(gardener.getEmail());
        output.setFirstName(gardener.getFirstName());
        output.setLastName(gardener.getLastName());
        output.setFields(gardener.getFields().stream().map(Util::fieldToProjection).toList());
        output.setPhoneNumber(gardener.getPhoneNumber());
        output.setLogin(gardener.getLogin());
        return output;
    }
}
