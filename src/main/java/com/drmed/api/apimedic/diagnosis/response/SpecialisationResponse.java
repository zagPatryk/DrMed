package com.drmed.api.apimedic.diagnosis.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "Specialisation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialisationResponse {
    @JsonProperty("Name")
    private String name;
}
