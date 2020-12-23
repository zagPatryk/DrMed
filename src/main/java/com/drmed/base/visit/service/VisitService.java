package com.drmed.base.visit.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.base.doctor.service.DoctorService;
import com.drmed.base.order.domain.Order;
import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.base.order.service.OrderService;
import com.drmed.base.patient.service.PatientService;
import com.drmed.base.visit.domain.Visit;
import com.drmed.base.visit.dto.NewVisitDto;
import com.drmed.base.visit.dto.VisitDto;
import com.drmed.base.visit.dto.VisitInfoDto;
import com.drmed.base.visit.mapper.VisitMapper;
import com.drmed.base.visit.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private VisitMapper visitMapper;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private OrderService orderService;

    public VisitDto addNewVisit(NewVisitDto newVisitDto) throws DoctorNotFoundException, PatientNotFoundException {
        Visit visit = new Visit.VisitBuilder()
                .setCode(newVisitDto.getCode())
                .setDateOfVisit(newVisitDto.getDateOfVisit())
                .setPatient(patientService.getPatientById(newVisitDto.getPatientId()))
                .setDoctor(doctorService.getDoctorById(newVisitDto.getDoctorId()))
                .build();
        return visitMapper.mapToVisitDto(visitRepository.saveVisit(visit));
    }

    public VisitDto updateVisit(VisitDto visitDto) throws DoctorNotFoundException, PatientNotFoundException, OrderNotFoundException {
        Visit visit = new Visit.VisitBuilder()
                .setCode(visitDto.getCode())
                .setDateOfVisit(visitDto.getDateOfVisit())
                .setPatient(patientService.getPatientById(visitDto.getPatient().getId()))
                .setDoctor(doctorService.getDoctorById(visitDto.getDoctor().getId()))
                .setOrderIdsList(visitDto.getOrderList().stream().map(OrderInfoDto::getId).collect(Collectors.toList()))
                .build();
        mapVisitIdsToVisit(visit);

        // update doctor list...
        // update patient list....
        // update order list.....

        return visitMapper.mapToVisitDto(visitRepository.saveVisit(visit));
    }

    private void mapVisitIdsToVisit(Visit visit) throws OrderNotFoundException {
        List<Order> orderList = new ArrayList<>();
        for (Long orderId : visit.getOrderIdsList()) {
            orderList.add(orderService.getOrderById(orderId));
        }
        visit.setOrderList(orderList);
    }

    public Visit getVisitById(Long visitId) throws VisitNotFoundException {
        return visitRepository.getVisitById(visitId);
    }

    public VisitDto getVisitDtoById(Long visitId) throws VisitNotFoundException {
        return visitMapper.mapToVisitDto(visitRepository.getVisitById(visitId));
    }

    public List<VisitInfoDto> getAllVisitsForPatient(Long patientId) {
        return visitMapper.mapToVisitInfoDtoList(visitRepository.getAllVisitsForPatient(patientId));
    }

    public List<VisitInfoDto> getVisitsByCodeContains(String code) {
        return visitMapper.mapToVisitInfoDtoList(visitRepository.getAllByCodeContains(code));
    }
}
