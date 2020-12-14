package com.drmed.base.workstation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkstationInfoDto {
    private Long id;
    private String code;
    private String name;
}
