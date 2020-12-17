package com.drmed.base.patient.repository;

import com.drmed.base.visit.repository.VisitHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "PATIENT")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PatientHibernate {
    @Id
    @Column(name = "PATIENT_ID", unique = true)
    @GeneratedValue
    @EqualsAndHashCode.Include
    @NotNull
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTHDAY")
    private LocalDate birthDate;

    @OneToMany(
            targetEntity = VisitHibernate.class,
            mappedBy = "patient",
            fetch = FetchType.EAGER
    )
    private List<VisitHibernate> visits;
}
