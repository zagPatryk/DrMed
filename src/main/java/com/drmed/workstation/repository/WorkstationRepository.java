package com.drmed.workstation.repository;

import com.drmed.additional.exceptions.dataNotFoundInDatabase.WorkstationNotFoundException;
import com.drmed.workstation.domain.Workstation;
import com.drmed.workstation.mapper.WorkstationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WorkstationRepository {
    @Autowired
    private WorkstationCrudRepository workstationCrudRepository;
    @Autowired
    private WorkstationMapper workstationMapper;

    public Workstation getWorkstationById(Long id) throws WorkstationNotFoundException {
        Optional<WorkstationHibernate> workstationHibernateOptional = workstationCrudRepository.findById(id);
        return workstationMapper.mapToWorkstation(workstationHibernateOptional.orElseThrow(WorkstationNotFoundException::new));
    }

    public List<Workstation> getAllWorkstations() {
        List<WorkstationHibernate> workstationHibernateList = new ArrayList<>();
        workstationCrudRepository.findAll().forEach(workstationHibernateList::add);
        return workstationMapper.mapToWorkstationList(workstationHibernateList);
    }

    public List<Workstation> getWorkstationByCode(String code) {
        List<WorkstationHibernate> workstationHibernateList = new ArrayList<>();
        workstationCrudRepository.findByCodeContaining(code).forEach(workstationHibernateList::add);
        return workstationMapper.mapToWorkstationList(workstationHibernateList);
    }

    public List<Workstation> getWorkstationByName(String name) {
        List<WorkstationHibernate> workstationHibernateList = new ArrayList<>();
        workstationCrudRepository.findByNameContaining(name).forEach(workstationHibernateList::add);
        return workstationMapper.mapToWorkstationList(workstationHibernateList);
    }

    public Workstation saveWorkstation(Workstation workstation) {
        WorkstationHibernate workstationHibernate = workstationMapper.mapToWorkstationHibernate(workstation);
        workstationCrudRepository.save(workstationHibernate);
        return workstationMapper.mapToWorkstation(workstationHibernate);
    }
}