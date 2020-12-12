package com.drmed.orderedTest.repository;

import com.drmed.additional.statuses.ResultStatus;
import com.drmed.order.repository.OrderHibernate;
import com.drmed.test.repository.TestHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ORDERED_TEST")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderedTestHibernate {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue
    @EqualsAndHashCode.Include
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    private OrderHibernate order;

    @ManyToOne
    @JoinColumn(name = "ORDERED_TEST_ID")
    private TestHibernate test;

    @Column(name = "TEST_STATUS")
    @Enumerated(EnumType.STRING)
    private ResultStatus testResultStatus;

    @Column(name = "RESULTS")
    private String results;
}
