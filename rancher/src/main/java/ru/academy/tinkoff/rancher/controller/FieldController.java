package ru.academy.tinkoff.rancher.controller;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.tinkoff.rancher.dto.FieldDTO;
import ru.academy.tinkoff.rancher.dto.FieldProjection;
import ru.academy.tinkoff.rancher.service.FieldService;
import ru.academy.tinkoff.rancher.util.Util;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("field")
public class FieldController {
    private FieldService fieldService;

    @Timed("createField")
    @PostMapping("/{gardenerId}")
    public FieldProjection createField(@PathVariable Long gardenerId,@RequestBody FieldDTO dto) {
        return Util.fieldToProjection(fieldService.createField(gardenerId, dto));
    }

    @PutMapping("/{id}")
    public FieldProjection updateField(@PathVariable Long id,@RequestBody FieldDTO dto) {
        return Util.fieldToProjection(fieldService.updateField(id, dto));
    }

    @GetMapping("/{id}")
    public FieldProjection findById(@PathVariable Long id) {
        return Util.fieldToProjection(fieldService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fieldService.delete(id);
    }

    @GetMapping
    public List<FieldProjection> findAll() {
        return fieldService.findAll().stream().map(Util::fieldToProjection).toList();
    }
}
