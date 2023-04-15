package ru.academy.tinkoff.landscape.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.academy.tinkoff.landscape.domain.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
