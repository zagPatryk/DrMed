package com.drmed.base.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInfoDto {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
}
