package ru.academy.tinkoff.handyman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.academy.tinkoff.handyman.document.User;
import ru.academy.tinkoff.handyman.dto.LandscapeUserDTO;
import ru.academy.tinkoff.handyman.dto.ClientDTO;
import ru.academy.tinkoff.handyman.repository.ClientRepository;
import ru.academy.tinkoff.handyman.util.Pair;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private static final String USER_TYPE = "handyman";
    private final ClientRepository clientRepository;
    @Autowired
    private Environment env;

    public User createUser(ClientDTO dto) throws MalformedURLException, URISyntaxException {
        try {
            var args = getRestArgs(dto, "/user");
            var request = args.first();
            var url = args.second();
            RestTemplate restTemplate = new RestTemplate();
            UUID id = restTemplate.postForObject(url.toURI(), request, UUID.class);
            User user = new User(
                    id,
                    dto.services(),
                    dto.latitude(),
                    dto.longitude()
            );
            return clientRepository.save(user);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        }
    }

    public User findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<User> findAll() {
        return clientRepository.findAll();
    }

    public void deleteById(UUID id) {
        clientRepository.deleteById(id);
    }

    public User updateUser(UUID id, ClientDTO dto) throws MalformedURLException, URISyntaxException {
        try {
            var args = getRestArgs(dto, "/user/" + id.toString());
            var request = args.first();
            var url = args.second();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(url.toURI(), request);
            User user = new User(
                    id,
                    dto.services(),
                    dto.latitude(),
                    dto.longitude()
            );
            return clientRepository.save(user);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        }
    }

    public Pair<HttpEntity<LandscapeUserDTO>, URL> getRestArgs(ClientDTO dto, String req) throws MalformedURLException {
        LandscapeUserDTO body = new LandscapeUserDTO(
                USER_TYPE,
                dto.login(),
                dto.email(),
                dto.phoneNumber(),
                dto.latitude(),
                dto.longitude()
        );
        URL domain = new URL(Objects.requireNonNull(env.getProperty("landscapeAPI.address")));
        URL url = new URL(domain, req);
        HttpEntity<LandscapeUserDTO> request = new HttpEntity<>(body);
        return Pair.of(request, url);
    }
}
