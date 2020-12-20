package com.drmed.base.orderedTest.service;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.*;
import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.base.doctor.repository.DoctorCrudRepository;
import com.drmed.base.doctor.service.DoctorService;
import com.drmed.base.order.domain.Order;
import com.drmed.base.order.dto.NewOrderDto;
import com.drmed.base.order.dto.OrderDto;
import com.drmed.base.order.repository.OrderCrudRepository;
import com.drmed.base.order.service.OrderService;
import com.drmed.base.orderedTest.domain.OrderedTest;
import com.drmed.base.orderedTest.dto.OrderedTestDto;
import com.drmed.base.orderedTest.repository.OrderedTestCrudRepository;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.repository.PatientCrudRepository;
import com.drmed.base.patient.service.PatientService;
import com.drmed.base.test.dto.NewTestDto;
import com.drmed.base.test.dto.TestDto;
import com.drmed.base.test.repository.TestCrudRepository;
import com.drmed.base.test.service.TestService;
import com.drmed.base.visit.dto.NewVisitDto;
import com.drmed.base.visit.dto.VisitDto;
import com.drmed.base.visit.repository.VisitCrudRepository;
import com.drmed.base.visit.service.VisitService;
import com.drmed.base.workstation.dto.NewWorkstationDto;
import com.drmed.base.workstation.dto.WorkstationDto;
import com.drmed.base.workstation.repository.WorkstationCrudRepository;
import com.drmed.base.workstation.service.WorkstationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderedTestServiceTestSuite {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private TestService testService;
    @Autowired
    private WorkstationService workstationService;
    @Autowired
    private OrderedTestService orderedTestService;
    @Autowired
    private TestCrudRepository testCrudRepository;
    @Autowired
    private WorkstationCrudRepository workstationCrudRepository;
    @Autowired
    private OrderCrudRepository orderCrudRepository;
    @Autowired
    private PatientCrudRepository patientCrudRepository;
    @Autowired
    private DoctorCrudRepository doctorCrudRepository;
    @Autowired
    private VisitCrudRepository visitCrudRepository;
    @Autowired
    private OrderedTestCrudRepository orderedTestCrudRepository;

    @Test
    void addOrderedTestToOrder() throws DataNotFoundInDatabase {
        // Given
        NewPatientDto newPatientDto = new NewPatientDto();
        PatientDto patientDto = patientService.addNewPatient(newPatientDto);

        NewDoctorDto newDoctorDto = new NewDoctorDto();
        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);

        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
                patientDto.getId(), doctorDto.getId());
        VisitDto visitDto = visitService.addNewVisit(newVisitDto);

        NewWorkstationDto w1 = new NewWorkstationDto();
        WorkstationDto w1dto = workstationService.addWorkstation(w1);
        List<Long> workstationIdsList = new ArrayList<>();
        workstationIdsList.add(w1dto.getId());

        NewTestDto t1 = new NewTestDto("testCode1", "testName1", workstationIdsList);
        TestDto t1Dto = testService.addTest(t1);

        NewOrderDto newOrderDto = new NewOrderDto("orderCode", visitDto.getId());
        OrderDto orderDto = orderService.addOrderForPatient(newOrderDto);

        // When
        OrderedTestDto orderedTestDto = orderedTestService.addOrderedTestToOrder(orderDto.getId(), t1Dto.getId());
        OrderedTestDto orderedTestFromBase = orderedTestService.getOrderedTestDtoById(orderedTestDto.getId());
        Order orderFromBase = orderService.getOrderById(orderDto.getId());

        // Then
        assertEquals(orderedTestDto.getId(), orderedTestFromBase.getId());
        assertEquals(t1Dto.getId(), orderedTestFromBase.getTest().getId());
        assertEquals(orderDto.getId(), orderedTestFromBase.getOrderId());
        assertNull(orderedTestFromBase.getResults());

        // Clean
        orderedTestCrudRepository.deleteById(orderedTestDto.getId());
        orderCrudRepository.deleteById(orderDto.getId());
        visitCrudRepository.deleteById(visitDto.getId());
        doctorCrudRepository.deleteById(doctorDto.getId());
        patientCrudRepository.deleteById(patientDto.getId());
        testCrudRepository.deleteById(t1Dto.getId());
        workstationCrudRepository.deleteById(w1dto.getId());
    }

    @Test
    void resultOrderedTest() {

    }

    @Test
    void cancelOrderedTest() throws DoctorNotFoundException, PatientNotFoundException, WorkstationNotFoundException, VisitNotFoundException, OrderedTestNotFoundException, TestNotFoundException, OrderNotFoundException {
        // Given
        NewPatientDto newPatientDto = new NewPatientDto();
        PatientDto patientDto = patientService.addNewPatient(newPatientDto);

        NewDoctorDto newDoctorDto = new NewDoctorDto();
        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);

        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
                patientDto.getId(), doctorDto.getId());
        VisitDto visitDto = visitService.addNewVisit(newVisitDto);

        NewWorkstationDto w1 = new NewWorkstationDto();
        WorkstationDto w1dto = workstationService.addWorkstation(w1);
        List<Long> workstationIdsList = new ArrayList<>();
        workstationIdsList.add(w1dto.getId());

        NewTestDto t1 = new NewTestDto("testCode1", "testName1", workstationIdsList);
        TestDto t1Dto = testService.addTest(t1);

        NewTestDto t2 = new NewTestDto("testCode2", "testName2", workstationIdsList);
        TestDto t2Dto = testService.addTest(t2);

        NewTestDto t3 = new NewTestDto("testCode3", "testName3", workstationIdsList);
        TestDto t3Dto = testService.addTest(t3);

        NewTestDto t4 = new NewTestDto("testCode4", "testName4", workstationIdsList);
        TestDto t4Dto = testService.addTest(t4);

        NewOrderDto newOrderDto = new NewOrderDto("orderCode", visitDto.getId());

        OrderDto orderDto = orderService.addOrderForPatient(newOrderDto);
        OrderedTestDto orderedTestDto1 = orderedTestService.addOrderedTestToOrder(orderDto.getId(), t1Dto.getId());

        OrderedTestDto orderedTestDto2 = orderedTestService.addOrderedTestToOrder(orderDto.getId(), t2Dto.getId());
        orderedTestService.resultOrderedTest(orderedTestDto2.getId(), "Result for orderedTestDto 2");

        OrderedTestDto orderedTestDto3 = orderedTestService.addOrderedTestToOrder(orderDto.getId(), t3Dto.getId());
        orderedTestService.cancelOrderedTest(orderedTestDto3.getId());

        OrderedTestDto orderedTestDto4 = orderedTestService.addOrderedTestToOrder(orderDto.getId(), t4Dto.getId());
        orderedTestService.resultOrderedTest(orderedTestDto4.getId(), "Result for orderedTestDto 2");
        orderedTestService.resultOrderedTest(orderedTestDto4.getId(), "Corrected result for orderedTestDto 2");

        // When
        orderedTestService.cancelOrderedTest(orderedTestDto1.getId());
        orderedTestService.cancelOrderedTest(orderedTestDto2.getId());
        orderedTestService.cancelOrderedTest(orderedTestDto3.getId());
        orderedTestService.cancelOrderedTest(orderedTestDto4.getId());

        Order orderFromBase = orderService.getOrderById(orderDto.getId());
        OrderedTest orderedTestFromBase1 = orderedTestService.getOrderedTestById(orderedTestDto1.getId());
        OrderedTest orderedTestFromBase2 = orderedTestService.getOrderedTestById(orderedTestDto2.getId());
        OrderedTest orderedTestFromBase3 = orderedTestService.getOrderedTestById(orderedTestDto3.getId());
        OrderedTest orderedTestFromBase4 = orderedTestService.getOrderedTestById(orderedTestDto4.getId());

        // Then
        assertEquals(ResultStatus.CANCELLED, orderedTestFromBase1.getTestResultStatus());
        assertEquals(ResultStatus.CANCELLED, orderedTestFromBase2.getTestResultStatus());
        assertEquals(ResultStatus.CANCELLED, orderedTestFromBase3.getTestResultStatus());
        assertEquals(ResultStatus.CANCELLED, orderedTestFromBase4.getTestResultStatus());
        assertTrue(orderedTestFromBase1.getResults().contains("Test cancelled."));
        assertTrue(orderedTestFromBase2.getResults().contains("Test cancelled."));
        assertTrue(orderedTestFromBase3.getResults().contains("Test cancelled."));
        assertTrue(orderedTestFromBase4.getResults().contains("Test cancelled."));
        assertEquals(ResultStatus.CANCELLED, orderFromBase.getOrderResultStatus());

        // Clean
        orderedTestCrudRepository.deleteById(orderedTestFromBase1.getId());
        orderedTestCrudRepository.deleteById(orderedTestFromBase2.getId());
        orderedTestCrudRepository.deleteById(orderedTestFromBase3.getId());
        orderedTestCrudRepository.deleteById(orderedTestFromBase4.getId());
        orderCrudRepository.deleteById(orderDto.getId());
        visitCrudRepository.deleteById(visitDto.getId());
        doctorCrudRepository.deleteById(doctorDto.getId());
        patientCrudRepository.deleteById(patientDto.getId());
        testCrudRepository.deleteById(t1Dto.getId());
        testCrudRepository.deleteById(t2Dto.getId());
        testCrudRepository.deleteById(t3Dto.getId());
        testCrudRepository.deleteById(t4Dto.getId());
        workstationCrudRepository.deleteById(w1dto.getId());
    }

//    @Test
//    void addManyOrderedTestsToOrder() throws WorkstationNotFoundException, VisitNotFoundException {
//        // Given
//        NewPatientDto newPatientDto = new NewPatientDto();
//        PatientDto patientDto = patientService.addNewPatient(newPatientDto);
//
//        NewDoctorDto newDoctorDto = new NewDoctorDto();
//        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);
//
//        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
//                patientDto.getId(), doctorDto.getId());
////        VisitDto visitDto = visitService.addNewVisit(newVisitDto);
//
//        NewWorkstationDto w1 = new NewWorkstationDto();
//        WorkstationDto w1dto = workstationService.addWorkstation(w1);
//        List<Long> workstationIdsList = new ArrayList<>();
//        workstationIdsList.add(w1dto.getId());
//
//        NewTestDto t1 = new NewTestDto("testCode1", "testName1", workstationIdsList);
//        NewTestDto t2 = new NewTestDto("testCode2", "testName2", workstationIdsList);
//        TestDto t1Dto = testService.addTest(t1);
//        TestDto t2Dto = testService.addTest(t2);
//
////        NewOrderDto newOrderDto = new NewOrderDto("orderCode", visitDto.getId());
////        OrderDto orderDto = orderService.addOrderForPatient(newOrderDto);
//
//        // When
//
////        Order orderFromBase = orderService.getOrderById(orderDto.getId());
//    }

    @Test
    void getAllOrderedTestsFromOrder() {
    }

    @Test
    void getOrderedTestById() {
    }
}