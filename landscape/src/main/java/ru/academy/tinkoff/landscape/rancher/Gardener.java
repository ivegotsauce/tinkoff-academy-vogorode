package ru.academy.tinkoff.landscape.rancher;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "gardener")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gardener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @JsonIgnoreProperties("gardener")
    @OneToMany(
            mappedBy = "gardener",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Field> fields;
    @Column(nullable = false)
    private String email;
    private String phoneNumber;
}
