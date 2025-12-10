package saam.challenge.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "admission_date")
    private OffsetDateTime admissionDate;

    @Column
    private BigDecimal salary;

    @Column
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
