package com.drmed.base.test.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.WorkstationNotFoundException;
import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.test.dto.NewTestDto;
import com.drmed.base.test.dto.TestDto;
import com.drmed.base.test.repository.TestCrudRepository;
import com.drmed.base.workstation.dto.NewWorkstationDto;
import com.drmed.base.workstation.dto.WorkstationDto;
import com.drmed.base.workstation.repository.WorkstationCrudRepository;
import com.drmed.base.workstation.service.WorkstationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TestServiceTestSuite {
    @Autowired
    private TestService testService;
    @Autowired
    private WorkstationService workstationService;
    @Autowired
    private TestCrudRepository testCrudRepository;
    @Autowired
    private WorkstationCrudRepository workstationCrudRepository;

    @Test
    public void addTest() throws WorkstationNotFoundException, TestNotFoundException {
        // Given
        NewWorkstationDto w1 = new NewWorkstationDto();
        NewWorkstationDto w2 = new NewWorkstationDto();
        NewWorkstationDto w3 = new NewWorkstationDto();

        WorkstationDto w1dto = workstationService.addWorkstation(w1);
        WorkstationDto w2dto = workstationService.addWorkstation(w2);
        WorkstationDto w3dto = workstationService.addWorkstation(w3);

        List<Long> workstationIdsList = new ArrayList<>();
        workstationIdsList.add(w1dto.getId());
        workstationIdsList.add(w2dto.getId());
        workstationIdsList.add(w3dto.getId());

        NewTestDto newTestDto = new NewTestDto("testCode", "testName", workstationIdsList);

        // When
        TestDto testDto = testService.addTest(newTestDto);
        com.drmed.base.test.domain.Test testFromDatabase = testService.getTestById(testDto.getId());

        // Then
        assertEquals(testDto.getId(), testFromDatabase.getId());
        assertEquals(newTestDto.getCode(), testFromDatabase.getCode());
        assertEquals(newTestDto.getName(), testFromDatabase.getName());
        assertEquals(ActivityStatus.ACTIVE, testFromDatabase.getTestActivityStatus());
        assertEquals(workstationIdsList, newTestDto.getPerformingWorkstationsIds());

        // Clean
        testCrudRepository.deleteById(testDto.getId());
        workstationCrudRepository.deleteById(w1dto.getId());
        workstationCrudRepository.deleteById(w2dto.getId());
        workstationCrudRepository.deleteById(w3dto.getId());
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void getTestById() {
    }

    @Test
    public void getTestsByCode() {
    }

    @Test
    public void getTestsByName() {
    }

    @Test
    public void getAllTests() {
    }
}