package ru.academy.tinkoff.handyman.controller;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.tinkoff.handyman.dto.UserDTO;
import ru.academy.tinkoff.handyman.entity.User;
import ru.academy.tinkoff.handyman.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {
    private UserService userService;

    @Timed("createUser")
    @PostMapping
    public User createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping
    public List<User> getAll(@RequestParam(required = false) Boolean sorted) {
        if (sorted != null && sorted) {
            return userService.findAllSorted();
        }
        return userService.findAll();
    }
}
