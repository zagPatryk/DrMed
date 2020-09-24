package com.drmed.domain.ordered;

import com.drmed.domain.additional.Status;
import com.drmed.domain.order.Order;
import com.drmed.domain.test.Test;
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
public class OrderedTest {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue
    @EqualsAndHashCode.Include
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ORDERED_TEST_ID")
    private Test test;

    @Column(name = "TEST_STATUS")
    @Enumerated(EnumType.STRING)
    private Status testStatus;

    @Column(name = "RESULTS")
    private String results;

    public OrderedTest(Order order, Test test) {
        this.order = order;
        this.test = test;
        this.testStatus = Status.PENDING;
        this.results = "";
    }
}
