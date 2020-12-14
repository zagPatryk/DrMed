package com.drmed.base.order.repository;

import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.orderedTest.repository.OrderedTestHibernate;
import com.drmed.base.patient.repository.PatientHibernate;
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

    @Column(name = "TRELLO_ORDER_CARD_ID")
    private String trelloOrderCardId;
}
