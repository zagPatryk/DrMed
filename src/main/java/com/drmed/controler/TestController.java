package com.drmed.controler;

import com.drmed.exceptions.WorkstationNotFoundException;
import com.drmed.domain.test.TestDto;
import com.drmed.mapper.TestMapper;
import com.drmed.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/test")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private TestMapper testMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllTests")
    public List<TestDto> getAllTests() {
        return testMapper.mapToTestDtoList(testService.findAllTests());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTestById")
    public TestDto getTestById(@RequestParam Long testId) {
        return testMapper.mapToTestDto(testService.findTestById(testId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addNewTest", consumes = APPLICATION_JSON_VALUE)
    public TestDto addNewTest(@RequestBody TestDto testDto) throws WorkstationNotFoundException {
        return testMapper.mapToTestDto(testService.saveTest(testDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTest", consumes = APPLICATION_JSON_VALUE)
    public TestDto updateTest(@RequestBody TestDto testDto) throws WorkstationNotFoundException {
        return testMapper.mapToTestDto(testService.saveTest(testDto));
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getOrderableTestsAtWorkstation")
//    public List<PatientDto> getOrderableTestsAtWorkstation(@RequestParam Long workstationId) {
//        return workstationMapper.mapToWorkstationDto(workstationService.getOrderableTestsAtWorkstation(workstationId));
//    }

//    @RequestMapping(method = RequestMethod.PUT, value = "addTestToWorkstation", consumes = APPLICATION_JSON_VALUE)
//    public TestDto addTestToWorkstation(@RequestBody Long testId) throws WorkstationNotFoundException {
//        return testMapper.mapToTestDto(testService.addTestToWorkstation(testId));
//    }

}
