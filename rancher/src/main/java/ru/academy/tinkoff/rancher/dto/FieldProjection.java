package ru.academy.tinkoff.rancher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.academy.tinkoff.rancher.domain.Gardener;

@Data
public class FieldProjection {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double area;
    @JsonIgnoreProperties("fields")
    private Gardener gardener;
}
