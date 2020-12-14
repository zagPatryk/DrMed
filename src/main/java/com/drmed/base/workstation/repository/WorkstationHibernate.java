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
            joinColumns = {@JoinColumn(name = "WORKSTATION_CODE", referencedColumnName = "WORKSTATION_CODE")},
            inverseJoinColumns = {@JoinColumn(name = "TEST_CODE", referencedColumnName = "TEST_CODE")}
    )
    private List<TestHibernate> availableTests = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "WORKSTATION_STATUS")
    private ActivityStatus workstationStatus = ActivityStatus.ACTIVE;
}
