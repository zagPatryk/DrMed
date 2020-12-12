package com.drmed.doctor.repository;

import com.drmed.additional.statuses.ActivityStatus;
import com.drmed.patient.repository.PatientHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DOCTOR")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DoctorHibernate {
    @Id
    @Column(name = "DOCTOR_ID")
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(name = "PRIMARY_ID")
    private String primaryId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany(
            targetEntity = PatientHibernate.class,
            mappedBy = "doctor"
    )
    private List<PatientHibernate> patients = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "DOCTOR_STATUS")
    private ActivityStatus doctorStatus;

    @Column(name = "EMAIL")
    private String email;
}