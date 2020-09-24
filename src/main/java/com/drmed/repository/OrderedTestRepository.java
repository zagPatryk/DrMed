package com.drmed.repository;

import com.drmed.domain.ordered.OrderedTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface OrderedTestRepository extends CrudRepository<OrderedTest, Long> {

}
