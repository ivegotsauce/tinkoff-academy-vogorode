package ru.academy.tinkoff.landscape.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.academy.tinkoff.landscape.rancher.Field;
import ru.academy.tinkoff.landscape.handyman.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Field field;
    @OneToOne
    private User user;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkType workType;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;
    @CreationTimestamp
    @Column(updatable = false)
    private Date creationDate;
}
