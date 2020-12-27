package com.drmed.base.doctor.dto;

import com.drmed.base.additional.statuses.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
    private ActivityStatus doctorStatus;
    private String email;
}