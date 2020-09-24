package com.drmed.repository;

import com.drmed.domain.test.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TestRepository extends CrudRepository<Test, Long> {

}
