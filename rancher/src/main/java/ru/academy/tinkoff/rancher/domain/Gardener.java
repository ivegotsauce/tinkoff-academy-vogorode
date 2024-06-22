package ru.academy.tinkoff.rancher.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import javax.persistence.*;

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
    private String login;
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
