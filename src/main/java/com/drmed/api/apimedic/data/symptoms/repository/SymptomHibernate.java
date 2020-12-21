package com.drmed.api.apimedic.data.symptoms.repository;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

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

    public SymptomHibernate(Long symptomId, String name) {
        this.symptomId = symptomId;
        this.name = name;
    }
}
