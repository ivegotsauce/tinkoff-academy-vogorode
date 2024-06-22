package ru.academy.tinkoff.landscape.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.academy.tinkoff.landscape.domain.Client;
import ru.academy.tinkoff.landscape.dto.UserDTO;
import ru.academy.tinkoff.landscape.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Timed("landscapeCreateUser")
    @PostMapping
    public UUID create(@RequestBody UserDTO dto) {return userService.createUser(dto);}

    @Timed("landscapeUpdateUser")
    @PutMapping("/{id}")
    public Client update(@PathVariable UUID id, @RequestBody UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @Timed("landscapeDeleteUser")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @Timed("landscapeGetAllUsers")
    @GetMapping
    public List<Client> findAll() {
        return userService.findAll();
    }

    @Timed("landscapeGetUser")
    @GetMapping("/{id}")
    public Client findById(@PathVariable UUID id) {
        Client client = userService.findById(id);
        if (client != null) {
            return client;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
