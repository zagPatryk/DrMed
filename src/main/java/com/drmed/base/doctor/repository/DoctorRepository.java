package com.drmed.base.doctor.repository;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.doctor.domain.Doctor;
import com.drmed.base.doctor.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorRepository {
    @Autowired
    private DoctorCrudRepository doctorCrudRepository;
    @Autowired
    private DoctorMapper doctorMapper;

    public Doctor getDoctorById(Long doctorId) throws DoctorNotFoundException {
        return doctorMapper.mapToDoctor(doctorCrudRepository.findById(doctorId).orElseThrow(DoctorNotFoundException::new));
    }

    public List<Doctor> getDoctorByCodeContains(String code) {
        List<DoctorHibernate> doctorHibernateList = new ArrayList<>();
        doctorCrudRepository.findByCodeContains(code).forEach(doctorHibernateList::add);
        return doctorMapper.mapToDoctorList(doctorHibernateList);
    }

    public List<Doctor> getDoctorByFirstNameContains(String firstName) {
        List<DoctorHibernate> doctorHibernateList = new ArrayList<>();
        doctorCrudRepository.findByFirstNameContains(firstName).forEach(doctorHibernateList::add);
        return doctorMapper.mapToDoctorList(doctorHibernateList);
    }

    public List<Doctor> getDoctorByLastNameContains(String lastName) {
        List<DoctorHibernate> doctorHibernateList = new ArrayList<>();
        doctorCrudRepository.findByLastNameContains(lastName).forEach(doctorHibernateList::add);
        return doctorMapper.mapToDoctorList(doctorHibernateList);
    }

    public List<Doctor> getDoctorByFirstNameContainsAndLastNameContaining(String firstName, String lastName) {
        List<DoctorHibernate> doctorHibernateList = new ArrayList<>();
        doctorCrudRepository.findByFirstNameContainsAndLastNameContains(firstName, lastName).forEach(doctorHibernateList::add);
        return doctorMapper.mapToDoctorList(doctorHibernateList);
    }

    public List<Doctor> getAllDoctors() {
        List<DoctorHibernate> doctorHibernateList = new ArrayList<>();
        doctorCrudRepository.findAll().forEach(doctorHibernateList::add);
        return doctorMapper.mapToDoctorList(doctorHibernateList);
    }

    public Doctor saveDoctor(Doctor doctor) {
        DoctorHibernate doctorHibernate = doctorMapper.mapToDoctorHibernate(doctor);
        doctorCrudRepository.save(doctorHibernate);
        return doctorMapper.mapToDoctor(doctorHibernate);
    }

    public void updateAllDoctorsEmails() {
        doctorCrudRepository.updateAllDoctorsEmails();
    }
}