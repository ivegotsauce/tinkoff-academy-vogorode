package ru.academy.tinkoff.rancher.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.tinkoff.rancher.domain.User;
import ru.academy.tinkoff.rancher.dto.UserDTO;
import ru.academy.tinkoff.rancher.service.UserService;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Timed("rancherCreateUser")
    @PostMapping
    public User save(@RequestBody UserDTO dto) throws MalformedURLException, URISyntaxException {
        return userService.createUser(dto);
    }

    @Timed("rancherUpdateUser")
    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody UserDTO dto) throws MalformedURLException, URISyntaxException {
        return userService.updateUser(id, dto);
    }

    @Timed("rancherGetUser")
    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @Timed("rancherGetAllUsers")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @Timed("rancherDeleteUser")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
    }
}
