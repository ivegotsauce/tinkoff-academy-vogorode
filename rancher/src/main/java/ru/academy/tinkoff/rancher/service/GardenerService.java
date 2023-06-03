package ru.academy.tinkoff.rancher.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.academy.tinkoff.rancher.domain.Field;
import ru.academy.tinkoff.rancher.domain.Gardener;
import ru.academy.tinkoff.rancher.dto.FieldDTO;
import ru.academy.tinkoff.rancher.dto.GardenerDTO;
import ru.academy.tinkoff.rancher.repository.GardenerRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GardenerService {
    private GardenerRepository gardenerRepository;
    private FieldService fieldService;

    @Transactional
    public Gardener createGardener(GardenerDTO dto) {
        Gardener gardener = new Gardener();
        dtoToGardener(dto, gardener);
        gardener = gardenerRepository.save(gardener);
        List<Field> fields = new ArrayList<>();
        for (FieldDTO fieldDTO : dto.fields()) {
            fields.add(fieldService.createField(gardener.getId(), fieldDTO));
        }
        gardener.setFields(fields);
        return gardenerRepository.save(gardener);
    }

    public Gardener updateGardener(Long id, GardenerDTO dto) {
        Gardener gardener = findById(id);
        dtoToGardener(dto, gardener);
        return gardenerRepository.save(gardener);
    }

    public void delete(Long id) {
        gardenerRepository.deleteById(id);
    }

    public List<Gardener> findAll() {
        return gardenerRepository.findAll();
    }

    public Gardener findById(Long id) {
        return gardenerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + id)
        );
    }

    private void dtoToGardener(GardenerDTO dto, Gardener gardener) {
        gardener.setEmail(dto.email());
        gardener.setPhoneNumber(dto.phoneNumber());
        gardener.setFirstName(dto.firstName());
        gardener.setLastName(dto.lastName());
    }
}
