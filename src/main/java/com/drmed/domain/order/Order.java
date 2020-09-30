package com.drmed.domain.order;

import com.drmed.domain.additional.Status;
import com.drmed.domain.ordered.OrderedTest;
import com.drmed.domain.patient.Patient;
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
public class Order {
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
    private Patient patient;

    @OneToMany(
            targetEntity = OrderedTest.class,
            mappedBy = "order"
    )
    private List<OrderedTest> orderedTests;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private Status orderStatus;

    public Status updateStatus() {
        if (orderedTests.stream().map(OrderedTest::getTestStatus)
                .anyMatch(orderedTestStatus -> orderedTestStatus == Status.PENDING)) {
            orderStatus = Status.PENDING;
            return Status.PENDING;
        } else if (orderedTests.stream().map(OrderedTest::getTestStatus)
                .anyMatch(orderedTestStatus -> orderedTestStatus == Status.CORRECTED)) {
            orderStatus = Status.CORRECTED;
            return Status.CORRECTED;
        } else if (orderedTests.stream().map(OrderedTest::getTestStatus)
                .allMatch(orderedTestStatus -> orderedTestStatus == Status.CANCELLED)) {
            orderStatus = Status.CANCELLED;
            return Status.CANCELLED;
        } else {
            return orderStatus;
        }
    }
}
