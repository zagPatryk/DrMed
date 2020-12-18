package com.drmed.base.order.repository;

import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.orderedTest.repository.OrderedTestHibernate;
import com.drmed.base.visit.repository.VisitHibernate;
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
    @Column(name = "ORDER_ID", unique = true)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @ManyToOne
    @JoinColumn(name = "VISIT_ID", referencedColumnName = "VISIT_ID")
    private VisitHibernate visit;

    @OneToMany(
            targetEntity = OrderedTestHibernate.class,
            mappedBy = "order",
            fetch = FetchType.EAGER
    )
    private List<OrderedTestHibernate> orderedTests;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private ResultStatus orderResultStatus;

    @Column(name = "TRELLO_ORDER_CARD_ID")
    private String trelloOrderCardId;
}
