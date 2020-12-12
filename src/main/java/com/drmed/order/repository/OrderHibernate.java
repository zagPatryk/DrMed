package com.drmed.order.repository;

import com.drmed.additional.statuses.ResultStatus;
import com.drmed.orderedTest.repository.OrderedTestHibernate;
import com.drmed.patient.repository.PatientHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PATIENT_ORDER")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderHibernate {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long id;

    @Column(name = "ORDER_CODE")
    private Integer code;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID")
    private PatientHibernate patient;

    @OneToMany(
            targetEntity = OrderedTestHibernate.class,
            mappedBy = "order"
    )
    private List<OrderedTestHibernate> orderedTests;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private ResultStatus orderResultStatus;
}