package com.drmed.base.orderedTest.service;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.base.doctor.repository.DoctorCrudRepository;
import com.drmed.base.doctor.service.DoctorService;
import com.drmed.base.order.domain.Order;
import com.drmed.base.order.dto.NewOrderDto;
import com.drmed.base.order.dto.OrderDto;
import com.drmed.base.order.repository.OrderCrudRepository;
import com.drmed.base.order.service.OrderService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        OrderedTestDto orderedTestFromBase = orderedTestService.getOrderedTestById(orderedTestDto.getId());
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



    @Test
    void resultOrderedTest() {
    }

    @Test
    void cancelOrderedTest() {
    }

    @Test
    void setResultsAndStatus() {
    }
}