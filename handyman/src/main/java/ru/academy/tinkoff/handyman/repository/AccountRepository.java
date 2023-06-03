package ru.academy.tinkoff.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.academy.tinkoff.handyman.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
