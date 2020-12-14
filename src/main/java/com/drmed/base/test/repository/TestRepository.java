package com.drmed.base.test.repository;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.base.test.domain.Test;
import com.drmed.base.test.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TestRepository {
    @Autowired
    private TestCrudRepository testCrudRepository;
    @Autowired
    private TestMapper testMapper;

    public Test getTestById(Long id) throws TestNotFoundException {
        Optional<TestHibernate> testHibernateOptional = testCrudRepository.findById(id);
        return testMapper.mapToTest(testHibernateOptional.orElseThrow(TestNotFoundException::new));
    }

    public List<Test> getAllTests() {
        List<TestHibernate> testHibernateList = new ArrayList<>();
        testCrudRepository.findAll().forEach(testHibernateList::add);
        return testMapper.mapToTestList(testHibernateList);
    }

    public List<Test> getTestByCode(String code) {
        List<TestHibernate> testHibernateList = new ArrayList<>();
        testCrudRepository.findByCodeContaining(code).forEach(testHibernateList::add);
        return testMapper.mapToTestList(testHibernateList);
    }

    public List<Test> getTestByName(String name) {
        List<TestHibernate> testHibernateList = new ArrayList<>();
        testCrudRepository.findByNameContaining(name).forEach(testHibernateList::add);
        return testMapper.mapToTestList(testHibernateList);
    }

    public Test saveTest(Test test) {
        TestHibernate testHibernate = testMapper.mapToTestHibernate(test);
        testCrudRepository.save(testHibernate);
        return testMapper.mapToTest(testHibernate);
    }
}
