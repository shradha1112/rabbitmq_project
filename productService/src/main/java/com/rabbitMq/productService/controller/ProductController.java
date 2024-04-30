package com.rabbitMq.productService.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitMq.productService.config.ProductServiceRabbitMQConfig;
import com.rabbitMq.productService.model.MessageBody;

@RestController
public class ProductController {

	@Autowired
	RabbitTemplate template;

	/******************************************
	 * TOPIC EXCHANGE
	 ***************************************************/

	@PostMapping(value = "/productMessage", produces = "application/json", consumes = "application/json")
	public void customerOrderTopicRequest(@RequestBody MessageBody message) {
		template.convertAndSend(ProductServiceRabbitMQConfig.TOPIC_EXCHANGE, "PRODUCT_ORDER", message);

		template.convertAndSend(ProductServiceRabbitMQConfig.TOPIC_EXCHANGE, "PRODUCT_COMPANY", message);

	}
}
