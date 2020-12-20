package com.drmed.api.apimedic.data.symptoms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymptomsResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
