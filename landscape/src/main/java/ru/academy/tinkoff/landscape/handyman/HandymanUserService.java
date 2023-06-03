package ru.academy.tinkoff.landscape.handyman;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class HandymanUserService {
    private HandymanUserRepository handymanUserRepository;

    public User findById(Long id) {
        return handymanUserRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + id)
        );
    }
}
