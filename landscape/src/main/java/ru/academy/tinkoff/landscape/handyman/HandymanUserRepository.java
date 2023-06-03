package ru.academy.tinkoff.landscape.handyman;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandymanUserRepository extends JpaRepository<User, Long> {
    List<User> findByOrderByLastNameAsc();
}
