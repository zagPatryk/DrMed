package com.drmed.base.visit.repository;

import com.drmed.base.doctor.repository.DoctorHibernate;
import com.drmed.base.patient.repository.PatientHibernate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "VISIT")
public class VisitHibernate {

    private Long id;
    private PatientHibernate patient;
    private DoctorHibernate doctor;
    private Timestamp dateOfVisit;
    private
}
