package com.drmed.base.order.service;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.base.doctor.repository.DoctorCrudRepository;
import com.drmed.base.doctor.service.DoctorService;
import com.drmed.base.order.domain.Order;
import com.drmed.base.order.dto.NewOrderDto;
import com.drmed.base.order.dto.OrderDto;
import com.drmed.base.order.repository.OrderCrudRepository;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.repository.PatientCrudRepository;
import com.drmed.base.patient.service.PatientService;
import com.drmed.base.test.repository.TestCrudRepository;
import com.drmed.base.test.service.TestService;
import com.drmed.base.visit.dto.NewVisitDto;
import com.drmed.base.visit.dto.VisitDto;
import com.drmed.base.visit.repository.VisitCrudRepository;
import com.drmed.base.visit.service.VisitService;
import com.drmed.base.workstation.repository.WorkstationCrudRepository;
import com.drmed.base.workstation.service.WorkstationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceTestSuite {
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

    @Test
    void addOrderForPatient() throws DataNotFoundInDatabase {
        // Given
        NewPatientDto newPatientDto = new NewPatientDto();
        PatientDto patientDto = patientService.addNewPatient(newPatientDto);

        NewDoctorDto newDoctorDto = new NewDoctorDto();
        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);

        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
                patientDto.getId(), doctorDto.getId());
        VisitDto visitDto = visitService.addNewVisit(newVisitDto);

        NewOrderDto newOrderDto = new NewOrderDto("orderCode", visitDto.getId());

        // When
        OrderDto orderDto = orderService.addOrderForPatient(newOrderDto);
        Order orderFromBase = orderService.getOrderById(orderDto.getId());

        // Then
        assertEquals(orderDto.getId(), orderFromBase.getId());
        assertEquals(visitDto.getId(), orderFromBase.getVisit().getId());
        assertEquals(new ArrayList<>(), orderFromBase.getOrderedTests());
        assertEquals(ResultStatus.PENDING, orderFromBase.getOrderResultStatus());
        assertNotNull(orderFromBase.getTrelloOrderCardId());

        // Clean
        orderCrudRepository.deleteById(orderDto.getId());
        visitCrudRepository.deleteById(visitDto.getId());
        doctorCrudRepository.deleteById(doctorDto.getId());
        patientCrudRepository.deleteById(patientDto.getId());
    }

//    @Test
//    void addTestToOrder() throws WorkstationNotFoundException, DoctorNotFoundException, PatientNotFoundException, VisitNotFoundException, OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException {
//        // Given
//        NewPatientDto newPatientDto = new NewPatientDto();
//        PatientDto patientDto = patientService.addNewPatient(newPatientDto);
//
//        NewDoctorDto newDoctorDto = new NewDoctorDto();
//        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);
//
//        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
//                patientDto.getId(), doctorDto.getId());
//        VisitDto visitDto = visitService.addNewVisit(newVisitDto);
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
//        List<Long> testIdsList = new ArrayList<>();
//        testIdsList.add(t1Dto.getId());
//        testIdsList.add(t2Dto.getId());
//
//        NewOrderDto newOrderDto = new NewOrderDto("orderCode", visitDto.getId());
//
//        OrderDto orderDto = orderService.addOrderForPatient(newOrderDto);
//        orderService.addTestToOrder(orderDto.getId(), testIdsList);
//        Order orderFromBase = orderService.getOrderById(orderDto.getId());
//    }

    @Test
    void getOrderById() {
    }

    @Test
    void getAllOrdersFromVisit() {
    }

    @Test
    void getAllOrdersByCodeContains() {
    }

    @Test
    void saveOrder() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void checkOrderStatus() {
    }
}