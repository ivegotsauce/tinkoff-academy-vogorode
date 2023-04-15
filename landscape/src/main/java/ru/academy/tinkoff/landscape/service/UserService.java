package ru.academy.tinkoff.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.academy.tinkoff.landscape.domain.User;
import ru.academy.tinkoff.landscape.dto.UserDTO;
import ru.academy.tinkoff.landscape.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UUID createUser(UserDTO dto) {
        User user = new User();
        saveUser(
                user,
                dto.userType(),
                dto.login(),
                dto.email(),
                dto.phoneNumber(),
                dto.userType().equals("handyman") ? 0 : 1,
                dto.latitude(),
                dto.longitude()
        );
        return user.getId();
    }

    public User updateUser(UUID id, UserDTO dto) {
        User user = new User();
        user.setId(id);
        return saveUser(
                user,
                dto.userType(),
                dto.login(),
                dto.email(),
                dto.phoneNumber(),
                dto.userType().equals("handyman") ? 0 : 1,
                dto.latitude(),
                dto.longitude()
        );
    }

    private User saveUser(User user, String userType, String login, String email,
                          String phoneNumber, int userTypeId, double latitude, double longitude) {
        user.setUserType(userType);
        user.setLogin(login);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setUserTypeId(userTypeId);
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        return userRepository.save(user);
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
