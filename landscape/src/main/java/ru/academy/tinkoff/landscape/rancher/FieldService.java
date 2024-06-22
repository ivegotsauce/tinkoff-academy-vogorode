package ru.academy.tinkoff.landscape.rancher;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class FieldService {
    private FieldRepository fieldRepository;

    public Field findById(Long id) {
        return fieldRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No field with id " + id)
        );
    }
}
