package com.drmed.domain.order;

import com.drmed.domain.additional.ResultStatus;
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
    private ResultStatus orderResultStatus;

    public ResultStatus updateStatus() {
        if (orderedTests.stream().map(OrderedTest::getTestResultStatus)
                .anyMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.PENDING)) {
            orderResultStatus = ResultStatus.PENDING;
            return ResultStatus.PENDING;
        } else if (orderedTests.stream().map(OrderedTest::getTestResultStatus)
                .anyMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.CORRECTED)) {
            orderResultStatus = ResultStatus.CORRECTED;
            return ResultStatus.CORRECTED;
        } else if (orderedTests.stream().map(OrderedTest::getTestResultStatus)
                .allMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.CANCELLED)) {
            orderResultStatus = ResultStatus.CANCELLED;
            return ResultStatus.CANCELLED;
        } else {
            return orderResultStatus;
        }
    }
}
