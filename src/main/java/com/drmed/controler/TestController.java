package com.drmed.controler;

import com.drmed.domain.test.TestDto;
import com.drmed.mapper.TestMapper;
import com.drmed.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.POST, value = "addNewTest")
    public TestDto addNewTest(@RequestBody TestDto testDto) {
        return null;
    }

}
