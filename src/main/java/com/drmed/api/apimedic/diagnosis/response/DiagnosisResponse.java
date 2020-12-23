package com.drmed.api.apimedic.diagnosis.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiagnosisResponse {
    @JsonProperty("Issue")
    private IssueResponse issueResponse;
    @JsonProperty("Specialisation")
    private List<SpecialisationResponse> specialisationResponseList;
}
