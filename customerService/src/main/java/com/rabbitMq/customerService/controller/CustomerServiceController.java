package com.rabbitMq.customerService.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitMq.customerService.configuration.CustomerRabbitMQConfig;
import com.rabbitMq.customerService.model.CustomerRequest;
import com.rabbitMq.customerService.model.MessageBody;

@RestController
public class CustomerServiceController {

	@Autowired
	RabbitTemplate template;
	
	
	//NORMAL EXCHANGE
	@PostMapping(value="/customerService", produces = "application/json", consumes= "application/json")
	public void customerOrderRequest(@RequestBody CustomerRequest customerRequest)
	{
		template.convertAndSend(CustomerRabbitMQConfig.EXCHANGE, CustomerRabbitMQConfig.ROUTING_KEYS, customerRequest);
	}
	
	
	//FANOUT EXCHANGE
	@PostMapping(value="/adminMessage", produces = "application/json", consumes= "application/json")
	public void customerRequest(@RequestBody MessageBody message)
	{
//		template.convertAndSend(CustomerRabbitMQConfig.EXCHANGE, CustomerRabbitMQConfig.ROUTING_KEYS, message);
	    template.convertAndSend(CustomerRabbitMQConfig.FANOUT_EXCHANGE, "", message);

	}
	
	//TOPIC EXCHANGE
	@PostMapping(value="/customerMessage", produces = "application/json", consumes= "application/json")
	public void customerOrderTopicRequest(@RequestBody MessageBody message)
	{
		template.convertAndSend(CustomerRabbitMQConfig.TOPIC_EXCHANGE, "COMPANY_PRODUCT", message);
	    template.convertAndSend(CustomerRabbitMQConfig.TOPIC_EXCHANGE, "COMPANY_ORDER", message);

	}
	
	
	//DIRECT EXCHANGE
	@PostMapping(value="/custPersonalMessage/{consumer}", produces = "application/json", consumes= "application/json")
	public void custOrderPersonal(@RequestBody MessageBody message,@PathVariable("consumer") String consumer)
	{
		if("order".equals(consumer))
		{
		
		template.convertAndSend(CustomerRabbitMQConfig.DIRECT_EXCHANGE, CustomerRabbitMQConfig.DI_ROUTE_CUSTOMER_ORDER, message);
		}
		else if("product".equals(consumer))
		{
			template.convertAndSend(CustomerRabbitMQConfig.DIRECT_EXCHANGE, CustomerRabbitMQConfig.DI_ROUTE_CUSTOMER_PRODUCT, message);

		}
	}
	
	
}
