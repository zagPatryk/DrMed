package com.drmed.base.doctor.domain;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.visit.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
    private ActivityStatus doctorStatus;
    private String email;

    private List<Long> visitIdList = new ArrayList<>();
    private List<Visit> visitList = new ArrayList<>();

    public Doctor(String code, String firstName, String lastName, ActivityStatus doctorStatus, String email) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorStatus = doctorStatus;
        this.email = email;
    }

    public Doctor(Long id, String code, String firstName, String lastName, ActivityStatus doctorStatus, String email) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorStatus = doctorStatus;
        this.email = email;
    }

    public Doctor(Long id, String code, String firstName, String lastName, ActivityStatus doctorStatus,
                  String email, List<Long> visitIdList) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorStatus = doctorStatus;
        this.email = email;
        this.visitIdList = visitIdList;
    }
}
