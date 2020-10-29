package com.drmed.service;

import com.drmed.domain.doctor.Doctor;
import com.drmed.domain.patient.Patient;
import com.drmed.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor findDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElseGet(Doctor::new);
    }

    public List<Doctor> findAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
    }

    public Doctor deleteDoctorById(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseGet(Doctor::new);
        doctor.safeDeleteDoctor();
        return doctorRepository.save(doctor);
    }

    public List<Patient> getDoctorPatients(Long doctorId) {
        return new ArrayList<>();
    }
}
