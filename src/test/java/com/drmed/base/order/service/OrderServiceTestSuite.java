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
import com.drmed.base.orderedTest.dto.OrderedTestDto;
import com.drmed.base.orderedTest.repository.OrderedTestCrudRepository;
import com.drmed.base.orderedTest.service.OrderedTestService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
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

        // Clean
        orderCrudRepository.deleteById(orderDto.getId());
        visitCrudRepository.deleteById(visitDto.getId());
        doctorCrudRepository.deleteById(doctorDto.getId());
        patientCrudRepository.deleteById(patientDto.getId());
    }

    @Test
    void cancelOrder() throws DataNotFoundInDatabase {
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
        OrderedTestDto orderedTestDto = orderedTestService.addOrderedTestToOrder(orderDto.getId(), t1Dto.getId());

        // When
        orderService.cancelOrder(orderDto.getId());
        Order orderFromBase = orderService.getOrderById(orderDto.getId());
        OrderedTestDto orderedTestFromBase = orderedTestService.getOrderedTestDtoById(orderedTestDto.getId());

        // Then
        assertEquals(ResultStatus.CANCELLED, orderFromBase.getOrderResultStatus());
        assertNotNull(orderedTestFromBase);

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
    void checkOrderStatus() {

    }

    @Test
    void getOrderById() {
    }

    @Test
    void getAllOrdersFromVisit() {
    }

    @Test
    void getAllOrdersByCodeContains() {
    }
}