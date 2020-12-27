package com.drmed.api.apimedic.controller;

import com.drmed.api.apimedic.diagnosis.dto.DiagnosisDto;
import com.drmed.api.apimedic.diagnosis.service.DiagnosisService;
import com.drmed.api.apimedic.symptoms.dto.SymptomDto;
import com.drmed.api.apimedic.symptoms.service.SymptomService;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/apimedic")
public class ApiMedicController {
    @Autowired
    private SymptomService symptomService;
    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping(value = "getAllSymptoms")
    public List<SymptomDto> getAllSymptoms() {
        return symptomService.getAllSymptomDtoList();
    }

    @GetMapping(value = "downloadSymptoms")
    public List<SymptomDto> downloadSymptomsToBase() throws InvalidKeyException, NoSuchAlgorithmException {
        return symptomService.downloadSymptomsToBase();
    }

    @GetMapping(value = "diagnosisForVisit")
    public DiagnosisDto createDiagnosisForVisit(@RequestParam Long visitId) throws InvalidKeyException, NoSuchAlgorithmException, VisitNotFoundException {
        return diagnosisService.createDiagnosisForVisit(visitId);
    }
}
