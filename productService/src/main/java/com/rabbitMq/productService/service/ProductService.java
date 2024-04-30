package com.rabbitMq.productService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitMq.productService.model.OrderedRequest;
import com.rabbitMq.productService.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	   public void saveProductRequest(OrderedRequest orderedRequest) {
	        productRepository.save(orderedRequest);
	    }
}
