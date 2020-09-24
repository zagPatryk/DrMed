package com.drmed;

import com.drmed.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
class DrMedApplicationTests {
    @Autowired
    OrderRepository orderRepository;

    @Test
    void test() {
        System.out.println(orderRepository.findByPatientId(3L));


    }

}
