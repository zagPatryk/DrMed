package com.drmed.mapper;

import com.drmed.domain.order.Order;
import com.drmed.domain.patient.Patient;
import com.drmed.domain.patient.PatientDto;
import com.drmed.repository.OrderRepository;
import com.drmed.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DoctorMapper doctorMapper;

    public Patient mapToPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        if (patientDto.getId() != null) {
            Optional<Patient> patientFromDb = patientRepository.findById(patient.getId());
            if (patientFromDb.isPresent()) {
                patient = patientFromDb.get();
            }
        }
        patient.setMRN(patientDto.getMRN());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setDoctor(doctorMapper.mapToDoctor(patientDto.getDoctor()));
        patient.setBirthDate(patientDto.getBirthDate());
        for (Long orderId : patientDto.getOrdersIds()) {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            if (optionalOrder.isPresent()) {
                patient.getOrders().add(optionalOrder.get());
            }
        }
        return patient;
    }

    public PatientDto mapToPatientDto(Patient patient) {
        System.out.println(patient);
        return new PatientDto(
                patient.getId(),
                patient.getMRN(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                doctorMapper.mapToDoctorDto(patient.getDoctor()),
                patient.getOrders().stream().map(Order::getId).collect(Collectors.toList())
        );
    }

    public List<PatientDto> mapToPatientDtoList(List<Patient> patientList) {
        List<PatientDto> patientDtoList = new ArrayList<>();
        patientList.stream().map(this::mapToPatientDto).forEach(patientDtoList::add);
        return patientDtoList;
    }
}
