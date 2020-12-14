package com.drmed.base.visit.service;

import com.drmed.base.visit.repository.VisitHibernate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService extends CrudRepository<VisitHibernate, Long> {

}
