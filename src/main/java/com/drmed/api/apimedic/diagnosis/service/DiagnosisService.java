package com.drmed.api.apimedic.diagnosis.service;

import com.drmed.api.apimedic.additional.exception.dataNotFoundInDatabase.DiagnosisNotFoundException;
import com.drmed.api.apimedic.client.ApiMedicClient;
import com.drmed.api.apimedic.diagnosis.domain.Diagnosis;
import com.drmed.api.apimedic.diagnosis.dto.DiagnosisDto;
import com.drmed.api.apimedic.diagnosis.mapper.DiagnosisMapper;
import com.drmed.api.apimedic.diagnosis.repository.DiagnosisRepository;
import com.drmed.api.apimedic.diagnosis.response.DiagnosisResponse;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.visit.domain.Visit;
import com.drmed.base.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisMapper diagnosisMapper;
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private ApiMedicClient apiMedicClient;
    @Autowired
    private VisitService visitService;

    public Diagnosis getDiagnosisById(Long diagnosisId) throws DiagnosisNotFoundException {
        return diagnosisRepository.getDiagnosisById(diagnosisId);
    }

    public DiagnosisDto getDiagnosisForVisit(Long visitId) throws DiagnosisNotFoundException {
        return diagnosisMapper.mapToDiagnosisDto(diagnosisRepository.getDiagnosisForVisit(visitId));
    }

    public DiagnosisDto createDiagnosisForPatient(Long visitId) throws InvalidKeyException, NoSuchAlgorithmException, VisitNotFoundException {
        Visit visit = visitService.getVisitById(visitId);
        DiagnosisResponse[] diagnosisResponse = apiMedicClient.getDiagnosisForVisit(visit);
        Diagnosis diagnosis = diagnosisMapper.mapToDiagnosis(diagnosisResponse[0]);
        Diagnosis diagnosisFromBase = visitService.saveDiagnosisForVisit(diagnosis, visit).getDiagnosis();
        return diagnosisMapper.mapToDiagnosisDto(diagnosisFromBase);
    }
}