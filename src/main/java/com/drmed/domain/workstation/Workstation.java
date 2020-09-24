package com.drmed.domain.workstation;

import com.drmed.domain.test.Test;
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
public class Workstation {
    @Id
    @NotNull
    @EqualsAndHashCode.Include
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "WORKSTATION_CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "JOIN_WORKSTATION_TEST",
            joinColumns = {@JoinColumn(name = "WORKSTATION_CODE", referencedColumnName = "WORKSTATION_CODE")},
            inverseJoinColumns = {@JoinColumn(name = "TEST_CODE", referencedColumnName = "TEST_CODE")}
    )
    private List<Test> availableTests = new ArrayList<>();
}
