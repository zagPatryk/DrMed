package com.drmed.api.apimedic.authorization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class TokenDto {
    @JsonProperty("Token")
    private String token;
    @JsonProperty("ValidThrough")
    private Long validThrough;
}
