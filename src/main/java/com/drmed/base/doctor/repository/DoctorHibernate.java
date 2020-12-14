package com.drmed.base.doctor.repository;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.visit.repository.VisitHibernate;
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
    @Column(name = "CODE")
    private String code;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany(
            targetEntity = VisitHibernate.class,
            mappedBy = "doctor"
    )
    private List<VisitHibernate> visitHibernateList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "DOCTOR_STATUS")
    private ActivityStatus doctorStatus;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TRELLO_BOARD_ID")
    private String trelloBoardId;
}
