package com.drmed.base.test.repository;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.orderedTest.repository.OrderedTestHibernate;
import com.drmed.base.workstation.repository.WorkstationHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
@Entity
@Transactional
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "JOIN_WORKSTATION_TEST",
            joinColumns = {@JoinColumn(name = "TEST_ID", referencedColumnName = "TEST_ID")},
            inverseJoinColumns = {@JoinColumn(name = "WORKSTATION_ID", referencedColumnName = "WORKSTATION_ID")}
    )
    private List<WorkstationHibernate> performingWorkstationList = new ArrayList<>();

    @OneToMany(
            targetEntity = OrderedTestHibernate.class,
            mappedBy = "test"
    )
    private List<OrderedTestHibernate> orderedTestList;

    @Enumerated(EnumType.STRING)
    @Column(name = "TEST_ACTIVITY_STATUS")
    private ActivityStatus testActivityStatus = ActivityStatus.ACTIVE;
}


