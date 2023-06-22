package ru.academy.tinkoff.landscape.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GardenerStatsDTO {
    private String gardenerLogin;
    private double minArea;
    private double maxArea;
    private double averageArea;
}
