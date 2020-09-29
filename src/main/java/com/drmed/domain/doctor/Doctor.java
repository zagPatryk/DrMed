package com.drmed.domain.doctor;

import com.drmed.domain.patient.Patient;
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
public class Doctor {

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
            targetEntity = Patient.class,
            mappedBy = "doctor"
    )
    private List<Patient> patients = new ArrayList<>();

//    @Enumerated(EnumType.STRING)
//    @Column(name = "DOCTOR_STATUS")
//    private Status doctorStatus = Status.ACTIVE;

    public Doctor(String primaryId, String firstName, String lastName) {
        this.primaryId = primaryId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Doctor(String primaryId, String firstName, String lastName, List<Patient> patients) {
        this.primaryId = primaryId;
        this.firstName = firstName;
        this.lastName = lastName;
        setDoctorForPatients(patients);
        this.patients = patients;
    }

    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
    }

    public void setDoctorForPatients(List<Patient> patients) {
        for (Patient patient : patients) {
            patient.setDoctor(this);
        }
    }

//    public void saveDeleteDoctor() {
//        this.doctorStatus = Status.DELETED;
//        for (Patient patient : patients) {
//            patient.disconnectDoctor();
//        }
//        patients.clear();
//    }
}
