package com.drmed.base.workstation.repository;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.test.repository.TestHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WORKSTATION")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WorkstationHibernate {
    @Id
    @NotNull
    @EqualsAndHashCode.Include
    @GeneratedValue
    @Column(name = "WORKSTATION_ID", unique = true)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "JOIN_WORKSTATION_TEST",
            joinColumns = {@JoinColumn(name = "WORKSTATION_ID", referencedColumnName = "WORKSTATION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TEST_ID", referencedColumnName = "TEST_ID")}
    )
    private List<TestHibernate> availableTests = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "WORKSTATION_STATUS")
    private ActivityStatus workstationStatus = ActivityStatus.ACTIVE;
}
