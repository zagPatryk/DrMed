package com.drmed.base.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewTestDto {
    private String code;
    private String name;
    private List<Long> performingWorkstationsIds;
}
