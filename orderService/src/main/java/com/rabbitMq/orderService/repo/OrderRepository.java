package com.rabbitMq.orderService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabbitMq.orderService.model.CustomerRequest;

@Repository
public interface OrderRepository extends JpaRepository<CustomerRequest, Long> {
    // You can add custom query methods here if needed
}
