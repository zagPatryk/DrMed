package com.drmed.domain.doctor;

import com.drmed.domain.patient.Patient;
import com.drmed.repository.DoctorRepository;
import com.drmed.repository.PatientRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorTestSuite {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testCreateDoctor() {
        // Given
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();

        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);

        Doctor doctor1 = new Doctor();
        Doctor doctor2 = new Doctor("MRNTEST2", "FNAM", "LNAM");
        Doctor doctor3 = new Doctor("MRNTEST3", "FNAM", "LNAM", patients);

        // When
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);
        doctorRepository.save(doctor3);

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        // Then
        Assert.assertTrue(doctorRepository.findById(doctor1.getId()).isPresent());
        Assert.assertTrue(doctorRepository.findById(doctor2.getId()).isPresent());
        Assert.assertTrue(doctorRepository.findById(doctor3.getId()).isPresent());

        // Clear up
        patientRepository.deleteById(patient1.getId());
        patientRepository.deleteById(patient2.getId());

        doctorRepository.deleteById(doctor1.getId());
        doctorRepository.deleteById(doctor2.getId());
        doctorRepository.deleteById(doctor3.getId());
    }

    @Test
    public void testReadDoctor() {
        // Given
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();

        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);

        Doctor doctor1 = new Doctor();
        Doctor doctor2 = new Doctor("MRNTEST2", "FNAM", "LNAM");
        Doctor doctor3 = new Doctor("MRNTEST3", "FNAM", "LNAM", patients);

        // When
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);
        doctorRepository.save(doctor3);

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        // Then
        Assert.assertEquals(doctor1, doctorRepository.findById(doctor1.getId()).get());
        Assert.assertEquals(doctor2, doctorRepository.findById(doctor2.getId()).get());
        Assert.assertEquals(doctor3, doctorRepository.findById(doctor3.getId()).get());

        // Clear up
        patientRepository.deleteById(patient1.getId());
        patientRepository.deleteById(patient2.getId());

        doctorRepository.deleteById(doctor1.getId());
        doctorRepository.deleteById(doctor2.getId());
        doctorRepository.deleteById(doctor3.getId());
    }

    @Test
    public void testUpdateDoctor() {
        // Given
        List<Patient> patients = new ArrayList<>();
        Patient patient1 = new Patient();
        patients.add(patient1);

        Doctor doctor1 = new Doctor();

        doctorRepository.save(doctor1);
        patientRepository.save(patient1);

        // When
        doctor1.setPrimaryId("PRIMARYID");
        doctor1.setFirstName("FNAM");
        doctor1.setLastName("LNAM");
        doctor1.setDoctorForPatients(patients);

        doctorRepository.save(doctor1);

        // Then
        Assert.assertEquals(doctor1, doctorRepository.findById(doctor1.getId()).get());

        // Clear up
        patientRepository.deleteById(patient1.getId());
        doctorRepository.deleteById(doctor1.getId());
    }

    @Test
    public void testDeleteDoctor() {
        // Given
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();

        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);

        Doctor doctor1 = new Doctor();
        Doctor doctor2 = new Doctor("MRNTEST2", "FNAM", "LNAM");
        Doctor doctor3 = new Doctor("MRNTEST3", "FNAM", "LNAM", patients);

        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);
        doctorRepository.save(doctor3);

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        // When
        patient1.disconnectDoctor();
        patient2.disconnectDoctor();

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        doctorRepository.deleteById(doctor1.getId());
        doctorRepository.deleteById(doctor2.getId());
        doctorRepository.deleteById(doctor3.getId());

        // Then
        Assert.assertFalse(doctorRepository.findById(doctor1.getId()).isPresent());
        Assert.assertFalse(doctorRepository.findById(doctor2.getId()).isPresent());
        Assert.assertFalse(doctorRepository.findById(doctor3.getId()).isPresent());

        Assert.assertTrue(patientRepository.findById(patient1.getId()).isPresent());
        Assert.assertTrue(patientRepository.findById(patient2.getId()).isPresent());

        // Clear up
        patientRepository.deleteById(patient1.getId());
        patientRepository.deleteById(patient2.getId());
    }

}