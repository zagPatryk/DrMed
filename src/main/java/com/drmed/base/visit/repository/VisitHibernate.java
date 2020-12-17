package com.drmed.base.visit.repository;

import com.drmed.base.doctor.repository.DoctorHibernate;
import com.drmed.base.order.repository.OrderHibernate;
import com.drmed.base.patient.repository.PatientHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "VISIT")
public class VisitHibernate {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "VISIT_ID", unique = true)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DATE_TIME")
    private LocalDate dateOfVisit;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID")
    private PatientHibernate patient;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCTOR_ID")
    private DoctorHibernate doctor;

    @OneToMany(
            targetEntity = OrderHibernate.class,
            mappedBy = "visit",
            fetch = FetchType.EAGER
    )
    private List<OrderHibernate> orders;
}
