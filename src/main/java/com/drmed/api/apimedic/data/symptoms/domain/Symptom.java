package com.drmed.api.apimedic.data.symptoms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Symptom {
    private Long externalId;
    private String name;
}
