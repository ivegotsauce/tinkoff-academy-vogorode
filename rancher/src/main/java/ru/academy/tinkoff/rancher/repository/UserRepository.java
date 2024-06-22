package ru.academy.tinkoff.rancher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.academy.tinkoff.rancher.domain.User;

import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
}
