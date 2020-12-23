package com.drmed.api.apimedic.symptoms.repository;

import com.drmed.base.visit.repository.VisitHibernate;
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
@Table(name = "API_MEDIC_SYMPTOMS")
public class SymptomHibernate {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "SYMPTOM_INTERNAL_ID", unique = true)
    private Long id;

    @Column(name = "SYMPTOM_EXTERNAL_ID")
    private Long symptomId;

    @Column(name = "SYMPTOM_NAME")
    private String name;

    @ManyToMany(mappedBy = "symptomsList", fetch = FetchType.EAGER)
    private List<VisitHibernate> visitList = new ArrayList<>();

    public SymptomHibernate(Long symptomId, String name) {
        this.symptomId = symptomId;
        this.name = name;
    }

    public SymptomHibernate(Long id, Long symptomId, String name) {
        this.id = id;
        this.symptomId = symptomId;
        this.name = name;
    }
}
