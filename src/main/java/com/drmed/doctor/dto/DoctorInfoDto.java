package com.drmed.doctor.dto;

import com.drmed.additional.statuses.ActivityStatus;
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
    private String primaryId;
    private String firstName;
    private String lastName;
    private ActivityStatus doctorStatus;
}
