package ru.academy.tinkoff.handyman.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.academy.tinkoff.handyman.dto.AccountDTO;
import ru.academy.tinkoff.handyman.dto.UserDTO;
import ru.academy.tinkoff.handyman.entity.Account;
import ru.academy.tinkoff.handyman.entity.Skill;
import ru.academy.tinkoff.handyman.entity.User;
import ru.academy.tinkoff.handyman.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private AccountService accountService;
    @Transactional
    public User createUser(UserDTO dto) {
        User user = new User();
        dtoToUser(dto, user);
        user = userRepository.save(user);
        List<Account> accounts = new ArrayList<>();
        for (AccountDTO accountDTO : dto.accounts()) {
            accounts.add(accountService.createAccount(user.getId(), accountDTO));
        }
        user.setAccounts(accounts);
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDTO dto) {
        User user = findById(id);
        dtoToUser(dto, user);
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + id)
        );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllSorted() {
        return userRepository.findByOrderByLastNameAsc();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private void dtoToUser(UserDTO dto, User user) {
        user.setEmail(dto.email());
        user.setPhoto(dto.photo());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setPhoneNumber(dto.phoneNumber());
        List<Skill> skills = new ArrayList<>();
        for (String skill : dto.skills()) {
            try {
                skills.add(Skill.valueOf(skill.substring(0, 1).toUpperCase()
                        + skill.substring(1).toLowerCase()));
            } catch (IllegalArgumentException ignored) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid skill provided: " + skill);
            }
        }
        user.setSkills(skills);
    }
}
