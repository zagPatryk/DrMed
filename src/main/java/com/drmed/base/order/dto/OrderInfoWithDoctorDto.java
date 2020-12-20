package com.drmed.base.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoWithDoctorDto extends OrderInfoDto {
    private Long doctorId;
}
