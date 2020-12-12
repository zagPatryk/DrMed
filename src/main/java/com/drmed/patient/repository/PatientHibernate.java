package com.drmed.patient.repository;

import com.drmed.doctor.repository.DoctorHibernate;
import com.drmed.order.repository.OrderHibernate;
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

    @Column(name = "MRN")
    private String MRN;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTHDAY")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCTOR_ID")
    private DoctorHibernate doctor;

    @OneToMany(
            targetEntity = OrderHibernate.class,
            mappedBy = "patient"
    )
    private List<OrderHibernate> orders;
}
