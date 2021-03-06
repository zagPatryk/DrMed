package com.drmed.base.test.controller;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.test.dto.NewTestDto;
import com.drmed.base.test.dto.TestDto;
import com.drmed.base.test.dto.TestInfoDto;
import com.drmed.base.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping(value = "getTestById")
    public TestDto getTestById(@RequestParam Long testId) throws DataNotFoundInDatabase {
        return testService.getTestDtoById(testId);
    }

    @GetMapping(value = "getTestsByCode")
    public List<TestInfoDto> getTestsByCode(@RequestParam String code) {
        return testService.getTestsByCode(code);
    }

    @GetMapping(value = "getTestsByName")
    public List<TestInfoDto> getTestsByName(@RequestParam String name) {
        return testService.getTestsByName(name);
    }

    @GetMapping(value = "getAllTests")
    public List<TestInfoDto> getAllTests() {
        return testService.getAllTests();
    }

    @PostMapping(value = "addTest", consumes = APPLICATION_JSON_VALUE)
    public TestDto addTest(@RequestBody NewTestDto newTestDto) throws DataNotFoundInDatabase {
        return testService.addTest(newTestDto);
    }

    @PutMapping(value = "updateTest", consumes = APPLICATION_JSON_VALUE)
    public TestDto updateTest(@RequestBody TestDto testDto) throws DataNotFoundInDatabase {
        return testService.updateTest(testDto);
    }
}
