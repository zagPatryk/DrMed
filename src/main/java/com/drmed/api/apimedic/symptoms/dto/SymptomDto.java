package com.drmed.api.apimedic.symptoms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymptomDto {
    private Long internalId;
    private Long externalId;
    private String name;
}
