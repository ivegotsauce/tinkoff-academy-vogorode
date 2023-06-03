package ru.academy.tinkoff.handyman.controller;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.tinkoff.handyman.document.User;
import ru.academy.tinkoff.handyman.dto.ClientDTO;
import ru.academy.tinkoff.handyman.service.ClientService;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @Timed("handymanCreateUser")
    @PostMapping
    public User save(@RequestBody ClientDTO dto) throws MalformedURLException, URISyntaxException {
        return clientService.createUser(dto);
    }

    @Timed("handymanUpdateUser")
    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody ClientDTO dto) throws MalformedURLException, URISyntaxException {
        return clientService.updateUser(id, dto);
    }


    @Timed("handymanGetUser")
    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id) {
        return clientService.findById(id);
    }

    @Timed("handymanGetAllUsers")
    @GetMapping
    public List<User> findAll() {
        return clientService.findAll();
    }

    @Timed("handymanDeleteUser")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        clientService.deleteById(id);
    }
}
