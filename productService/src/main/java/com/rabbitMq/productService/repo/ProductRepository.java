package com.rabbitMq.productService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabbitMq.productService.model.OrderedRequest;

public interface ProductRepository extends JpaRepository<OrderedRequest, String> {

}
