package ru.academy.tinkoff.rancher.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.tinkoff.rancher.domain.Gardener;
import ru.academy.tinkoff.rancher.dto.GardenerDTO;
import ru.academy.tinkoff.rancher.dto.GardenerOutput;
import ru.academy.tinkoff.rancher.service.GardenerService;
import ru.academy.tinkoff.rancher.util.Util;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("gardener")
public class GardenerController {
    private GardenerService gardenerService;

    @PostMapping
    public GardenerOutput createGardener(@RequestBody GardenerDTO dto) {
        return Util.gardenerToOutput(gardenerService.createGardener(dto));
    }

    @PutMapping("/{id}")
    public GardenerOutput updateGardener(@RequestBody GardenerDTO dto, @PathVariable Long id) {
        return Util.gardenerToOutput(gardenerService.updateGardener(id, dto));
    }

    @GetMapping("/{id}")
    public GardenerOutput getById(@PathVariable Long id) {
        return Util.gardenerToOutput(gardenerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gardenerService.delete(id);
    }

    @GetMapping
    public List<GardenerOutput> findAll() {
        return gardenerService.findAll().stream().map(Util::gardenerToOutput).toList();
    }
}
