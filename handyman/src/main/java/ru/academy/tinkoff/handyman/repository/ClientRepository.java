package ru.academy.tinkoff.handyman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.academy.tinkoff.handyman.document.User;

import java.util.UUID;

public interface ClientRepository extends MongoRepository<User, UUID> {
}
