package com.rabbitMq.orderService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitMq.orderService.model.CustomerRequest;
import com.rabbitMq.orderService.repo.OrderRepository;


@Service
public class OrderService {

	
	@Autowired
    private OrderRepository orderRepository;

    public void saveCustomerRequest(CustomerRequest customerRequest) {
        orderRepository.save(customerRequest);
    }
}
