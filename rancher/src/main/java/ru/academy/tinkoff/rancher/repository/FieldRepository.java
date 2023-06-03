package ru.academy.tinkoff.rancher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.academy.tinkoff.rancher.domain.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
}
