package com.drmed.domain.test;

import com.drmed.domain.ordered.OrderedTest;
import com.drmed.domain.workstation.Workstation;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@NamedNativeQuery(
//        name = "SingleTest.receivePerformingWorkstationsList",
//        query = "SELECT * " +
//                "FROM workstation as w " +
//                "INNER JOIN join_workstation_test wt ON wt.workstation_id = w.workstation_id " +
//                "INNER JOIN test t ON t.test_id = wt.test_id " +
//                "WHERE  t.test_id = :ID",
//        resultClass = Workstation.class
//)
//@NamedQuery(
//        name = "SingleTest.retrieveTestById",
//        query = "FROM SingleTest t WHERE t.id = :ID"
//)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SINGLE_TEST")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Test {
    @Id
    @NotNull
    @GeneratedValue
    @EqualsAndHashCode.Include
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TEST_CODE")
    private String code;

    @Column(name = "TEST_NAME")
    private String name;

    @ManyToMany(mappedBy = "availableTests")
    private List<Workstation> performingWorkstations = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "ORDERED_TEST_ID")
    private List<OrderedTest> orderedTest;
}
