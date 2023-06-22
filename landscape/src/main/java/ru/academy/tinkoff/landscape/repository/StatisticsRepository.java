package ru.academy.tinkoff.landscape.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.academy.tinkoff.landscape.domain.Order;
import ru.academy.tinkoff.landscape.dto.BankStatsDTO;
import ru.academy.tinkoff.landscape.dto.GardenerStatsDTO;

import java.util.List;

@Repository
public interface StatisticsRepository extends CrudRepository<Order, Long> {
    @Query(
    """
    select new ru.academy.tinkoff.landscape.dto.GardenerStatsDTO(
        g.login, min(st_area(f.area)), max(st_area(f.area)), avg(st_area(f.area))
    )
    from Field f
    inner join Gardener g on g.id = f.gardener.id
    group by g.login
    """
    )
    List<GardenerStatsDTO> getGardenerStats();

    @Query(
    """
    select new ru.academy.tinkoff.landscape.dto.BankStatsDTO(
        a.paymentMethod, min(u.creationTime), max(u.creationTime)
    )
    from Account a
    inner join User u on u.id = a.user.id
    group by a.paymentMethod
    """
    )
    List<BankStatsDTO> getBankStats();
}
