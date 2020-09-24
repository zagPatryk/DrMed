package com.drmed.service;

import com.drmed.domain.test.Test;
import com.drmed.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> findAllTests() {
        List<Test> testList = new ArrayList<>();
        testRepository.findAll().forEach(testList::add);
        return testList;
    }

    public Test findTestById(Long id) {
        return testRepository.findById(id).orElseGet(Test::new);
    }
}
