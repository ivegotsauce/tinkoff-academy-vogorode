package ru.academy.tinkoff.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.academy.tinkoff.landscape.domain.Client;
import ru.academy.tinkoff.landscape.dto.UserDTO;
import ru.academy.tinkoff.landscape.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UUID createUser(UserDTO dto) {
        Client client = new Client();
        saveUser(
                client,
                dto.userType(),
                dto.login(),
                dto.email(),
                dto.phoneNumber(),
                dto.userType().equals("handyman") ? 0 : 1,
                dto.latitude(),
                dto.longitude()
        );
        return client.getId();
    }

    public Client updateUser(UUID id, UserDTO dto) {
        Client client = new Client();
        client.setId(id);
        return saveUser(
                client,
                dto.userType(),
                dto.login(),
                dto.email(),
                dto.phoneNumber(),
                dto.userType().equals("handyman") ? 0 : 1,
                dto.latitude(),
                dto.longitude()
        );
    }

    private Client saveUser(Client client, String userType, String login, String email,
                            String phoneNumber, int userTypeId, double latitude, double longitude) {
        client.setUserType(userType);
        client.setLogin(login);
        client.setEmail(email);
        client.setPhoneNumber(phoneNumber);
        client.setUserTypeId(userTypeId);
        client.setLatitude(latitude);
        client.setLongitude(longitude);
        return userRepository.save(client);
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public Client findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<Client> findAll() {
        return userRepository.findAll();
    }
}
