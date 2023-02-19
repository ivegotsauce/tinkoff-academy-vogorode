package ru.academy.tinkoff.rancher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class GardenerOutput {
    private String firstName;
    private String lastName;
    @JsonIgnoreProperties("gardener")
    private List<FieldProjection> fields;
    private String email;
    private String phoneNumber;
    private Long id;
}
