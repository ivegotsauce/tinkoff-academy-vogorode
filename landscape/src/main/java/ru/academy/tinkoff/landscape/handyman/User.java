package ru.academy.tinkoff.landscape.handyman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    private String phoneNumber;
    @JsonIgnoreProperties("user")
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Account> accounts;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photo;
    @CreationTimestamp
    @Column(updatable = false)
    private Date creationTime;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;
}
