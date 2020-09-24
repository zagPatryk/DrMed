package com.drmed.repository;

import com.drmed.domain.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Override
    List<Order> findAll();

//    @Query("SELECT order_id, order_code, order_status FROM PATIENT_ORDER WHERE PATIENT_ID = (:name)")
//    List<Order> findByPatientId(@Param("name") Long patientId);

    List<Order> findByPatientId(Long id);
}
