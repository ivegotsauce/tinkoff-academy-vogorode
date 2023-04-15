package ru.academy.tinkoff.handyman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.academy.tinkoff.handyman.domain.User;

import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
}
