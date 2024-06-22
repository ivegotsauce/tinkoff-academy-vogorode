package ru.academy.tinkoff.landscape.rancher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

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
