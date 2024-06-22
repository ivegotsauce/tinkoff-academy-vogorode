package ru.academy.tinkoff.rancher.service;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.academy.tinkoff.rancher.domain.Field;
import ru.academy.tinkoff.rancher.domain.Gardener;
import ru.academy.tinkoff.rancher.dto.FieldDTO;
import ru.academy.tinkoff.rancher.repository.FieldRepository;
import ru.academy.tinkoff.rancher.repository.GardenerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private GardenerRepository gardenerRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public Field createField(Long gardenerId, FieldDTO dto) {
        Gardener gardener = gardenerRepository.findById(gardenerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + gardenerId)
        );
        Field field = new Field();
        dtoToField(dto, field);
        field.setGardener(gardener);
        return fieldRepository.save(field);
    }

    public Field findById(Long id) {
        return fieldRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No field with id " + id)
        );
    }

    public Field updateField(Long id, FieldDTO dto) {
        Field field = findById(id);
        dtoToField(dto, field);
        return fieldRepository.save(field);
    }

    public void delete(Long id) {
        fieldRepository.deleteById(id);
    }

    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    private void dtoToField(FieldDTO dto, Field field) {
        field.setAddress(dto.address());
        field.setLongitude(dto.longitude());
        field.setLatitude(dto.latitude());
        try {
            field.setArea(geometryFactory.createPolygon(dto.coordinates()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
