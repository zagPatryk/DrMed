package com.drmed.service;

import com.drmed.domain.doctor.Doctor;
import com.drmed.domain.patient.Patient;
import com.drmed.exceptions.DoctorNotFoundException;
import com.drmed.repository.DoctorRepository;
import com.drmed.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseGet(Patient::new);
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        patientRepository.findAll().forEach(patientList::add);
        return patientList;
    }

    public Patient savePatient(Patient patient) throws DoctorNotFoundException {
        patientRepository.save(patient);
        Doctor doctor = doctorRepository.findById(patient.getDoctor().getId()).orElseThrow(DoctorNotFoundException::new);
        doctor.addPatient(patient);
        doctorRepository.save(doctor);
        return patient;
    }
}
