package com.drmed.base.doctor.dto;

import com.drmed.base.additional.statuses.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String primaryId;
    private String firstName;
    private String lastName;
    private ActivityStatus doctorStatus;
    private String email;
    private List<Long> patientsIds = new ArrayList<>();
}
