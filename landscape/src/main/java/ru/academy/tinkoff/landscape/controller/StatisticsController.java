package ru.academy.tinkoff.landscape.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.academy.tinkoff.landscape.dto.BankStatsDTO;
import ru.academy.tinkoff.landscape.dto.GardenerStatsDTO;
import ru.academy.tinkoff.landscape.service.StatisticsService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("stats")
public class StatisticsController {

    private StatisticsService statisticsService;

    @GetMapping("gardener-stats")
    public List<GardenerStatsDTO> getGardenerStats() {
        return statisticsService.getGardenerStats();
    }

    @GetMapping("bank-stats")
    public List<BankStatsDTO> getBankStats() {
        return statisticsService.getBankStats();
    }
}
