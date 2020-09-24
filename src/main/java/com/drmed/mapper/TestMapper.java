package com.drmed.mapper;

import com.drmed.domain.ordered.OrderedTest;
import com.drmed.domain.test.Test;
import com.drmed.domain.test.TestDto;
import com.drmed.domain.workstation.Workstation;
import com.drmed.repository.OrderedTestRepository;
import com.drmed.repository.TestRepository;
import com.drmed.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TestMapper {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private WorkstationRepository workstationRepository;
    @Autowired
    private OrderedTestRepository orderedTestRepository;

    public Test mapToTest(TestDto testDto) {
        Test test = new Test();
        if (testDto.getId() != null) {
            Optional<Test> testFromDb = testRepository.findById(testDto.getId());
            if (testFromDb.isPresent()) {
                test = testFromDb.get();
            }
        }
        test.setCode(testDto.getCode());
        test.setName(testDto.getName());
        for (Long workstationId : testDto.getPerformingWorkstationsIds()) {
            Optional<Workstation> optionalWorkstation = workstationRepository.findById(workstationId);
            if (optionalWorkstation.isPresent()) {
                test.getPerformingWorkstations().add(optionalWorkstation.get());
            }
        }
        for (Long orderedTestId : testDto.getOrderedTest()) {
            Optional<OrderedTest> optionalOrderedTest = orderedTestRepository.findById(orderedTestId);
            if (optionalOrderedTest.isPresent()) {
                test.getOrderedTest().add(optionalOrderedTest.get());
            }
        }
        return test;
    }

    public TestDto mapToTestDto(Test test) {
        return new TestDto(
          test.getId(),
          test.getCode(),
          test.getName(),
          test.getPerformingWorkstations().stream()
                  .map(Workstation::getId).
                  collect(Collectors.toList()),
          test.getOrderedTest().stream()
                  .map(OrderedTest::getId)
                  .collect(Collectors.toList())
        );
    }

    public List<TestDto> mapToTestDtoList(List<Test> testList) {
        return testList.stream()
                .map(this::mapToTestDto)
                .collect(Collectors.toList());
    }
}
