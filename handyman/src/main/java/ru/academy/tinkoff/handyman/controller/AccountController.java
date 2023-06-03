package ru.academy.tinkoff.handyman.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.tinkoff.handyman.dto.AccountDTO;
import ru.academy.tinkoff.handyman.entity.Account;
import ru.academy.tinkoff.handyman.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("account")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.findAll();
    }

    @PostMapping("/{userId}")
    public Account createAccount(@PathVariable Long userId, @RequestBody AccountDTO dto) {
        return accountService.createAccount(userId, dto);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, AccountDTO dto) {
        return accountService.updateAccount(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
