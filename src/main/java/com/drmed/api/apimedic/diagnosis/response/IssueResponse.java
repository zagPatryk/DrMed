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
@JsonRootName(value = "Issue")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueResponse {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("ProfName")
    private String professionalName;
    @JsonProperty("Icd")
    private String icd;
    @JsonProperty("IcdName")
    private String icdName;
}
