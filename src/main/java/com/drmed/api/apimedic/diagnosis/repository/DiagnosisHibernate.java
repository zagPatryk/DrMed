package com.drmed.api.apimedic.diagnosis.repository;

import com.drmed.base.visit.repository.VisitHibernate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "API_MEDIC_DIAGNOSIS")
public class DiagnosisHibernate {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @NotNull
    @Column(name = "DIAGNOSIS_ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PROF_NAME")
    private String professionalName;

    @Column(name = "ICD_CODE")
    private String icdCode;

    @Column(name = "ICD_NAME")
    private String icdName;

    @ElementCollection
    @Column(name = "SPECIALIST_NAME_LIST")
    private List<String> specialistNameList;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private VisitHibernate visit;

    public DiagnosisHibernate(Long id, String name, String professionalName, String icdCode, String icdName, List<String> specialistNameList) {
        this.id = id;
        this.name = name;
        this.professionalName = professionalName;
        this.icdCode = icdCode;
        this.icdName = icdName;
        this.specialistNameList = specialistNameList;
    }
}
