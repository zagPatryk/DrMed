package com.drmed.domain.doctor;

import com.drmed.domain.additional.ActivityStatus;
import com.sun.istack.NotNull;
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
    @NotNull
    private String primaryId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private List<Long> patientsIds = new ArrayList<>();
    private ActivityStatus activityStatus = ActivityStatus.ACTIVE;

    public DoctorDto(String primaryId, String firstName, String lastName, List<Long> patientsIds) {
        this.primaryId = primaryId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patientsIds = patientsIds;
    }

    public DoctorDto(String primaryId, String firstName, String lastName) {
        this.primaryId = primaryId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public DoctorDto(Long id, String primaryId, String firstName, String lastName) {
        this.id = id;
        this.primaryId = primaryId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
