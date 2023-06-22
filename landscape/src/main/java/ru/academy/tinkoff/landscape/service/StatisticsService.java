package ru.academy.tinkoff.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.academy.tinkoff.landscape.dto.BankStatsDTO;
import ru.academy.tinkoff.landscape.dto.GardenerStatsDTO;
import ru.academy.tinkoff.landscape.repository.StatisticsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public List<GardenerStatsDTO> getGardenerStats() {
        return statisticsRepository.getGardenerStats();
    }

    public List<BankStatsDTO> getBankStats() {
        return statisticsRepository.getBankStats();
    }
}
