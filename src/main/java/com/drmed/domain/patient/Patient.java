package com.drmed.domain.patient;

import com.drmed.domain.doctor.Doctor;
import com.drmed.domain.order.Order;
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
public class Patient {

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
    private Doctor doctor;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "patient"
    )
    private List<Order> orders;

    public void disconnectDoctor() {
        this.doctor.getPatients().remove(this);
        this.doctor = null;
    }
}
