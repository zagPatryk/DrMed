package com.drmed.api.apimedic.symptoms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Symptom {
    private Long internalId;
    private Long externalId;
    private String name;

    public Symptom(Long externalId, String name) {
        this.externalId = externalId;
        this.name = name;
    }
}
