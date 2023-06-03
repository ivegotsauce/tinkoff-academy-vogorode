package ru.academy.tinkoff.landscape.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.academy.tinkoff.landscape.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
