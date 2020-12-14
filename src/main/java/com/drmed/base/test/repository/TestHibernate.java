package com.drmed.base.test.repository;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.orderedTest.repository.OrderedTestHibernate;
import com.drmed.base.workstation.repository.WorkstationHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SINGLE_TEST")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TestHibernate implements Serializable {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @NotNull
    @Column(name = "TEST_ID", unique = true)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TEST_NAME")
    private String name;

    @ManyToMany(mappedBy = "availableTests")
    private List<WorkstationHibernate> performingWorkstationList = new ArrayList<>();

    @OneToMany(
            targetEntity = OrderedTestHibernate.class,
            mappedBy = "test",
            fetch = FetchType.EAGER
    )
    private List<OrderedTestHibernate> orderedTestList;

    @Enumerated(EnumType.STRING)
    @Column(name = "TEST_ACTIVITY_STATUS")
    private ActivityStatus testActivityStatus = ActivityStatus.ACTIVE;
}


