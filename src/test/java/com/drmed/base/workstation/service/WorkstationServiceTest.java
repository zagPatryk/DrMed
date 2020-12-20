package com.drmed.base.workstation.service;


import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.WorkstationNotFoundException;
import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.workstation.dto.NewWorkstationDto;
import com.drmed.base.workstation.dto.WorkstationDto;
import com.drmed.base.workstation.repository.WorkstationCrudRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkstationServiceTest {
    @Autowired
    private WorkstationService workstationService;
    @Autowired
    private WorkstationCrudRepository workstationCrudRepository;

    @Test
    public void addWorkstation() throws WorkstationNotFoundException {
        // Given
        NewWorkstationDto newWorkstationDto = new NewWorkstationDto("workCode", "workstation Name");

        // When
        WorkstationDto workstationDto = workstationService.addWorkstation(newWorkstationDto);
        WorkstationDto workstationFromDatabase = workstationService.getWorkstationDtoById(workstationDto.getId());

        // Then
        assertEquals(workstationDto.getId(), workstationFromDatabase.getId());
        assertEquals(newWorkstationDto.getCode(), workstationFromDatabase.getCode());
        assertEquals(newWorkstationDto.getName(), workstationFromDatabase.getName());
        assertTrue(workstationFromDatabase.getAvailableTests().isEmpty());
        assertEquals(ActivityStatus.ACTIVE, workstationFromDatabase.getActivityStatus());

        // Clean
        workstationCrudRepository.deleteById(workstationDto.getId());
    }

    @Test
    public void updateWorkstation() {
    }

    @Test
    public void getWorkstationById() {
    }

    @Test
    public void getWorkstationsByCode() {
    }

    @Test
    public void getWorkstationsByName() {
    }

    @Test
    public void getAllWorkstation() {
    }
}