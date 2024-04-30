package com.rabbitMq.orderService.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.rabbitMq.orderService.config.OrderServiceRabbitMQConfig;
import com.rabbitMq.orderService.model.MessageBody;
import com.rabbitMq.orderService.model.OrderedRequest;

@RestController
public class OrderServiceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*************** TOPIC EXCHANGE **********************************************/

    @PostMapping(value = "/orderService", produces = "application/json", consumes = "application/json")
    public void orderServiceRequest(@RequestBody OrderedRequest orderRequest) {
    	System.out.println("called");
        rabbitTemplate.convertAndSend(OrderServiceRabbitMQConfig.EXCHANGE, OrderServiceRabbitMQConfig.ROUTING_KEY, orderRequest);
    }
    
    
    @PostMapping(value = "/orderMessage", produces = "application/json", consumes = "application/json")
    public void orderRequest(@RequestBody com.rabbitMq.orderService.model.MessageBody message) {
    	System.out.println("called");
        rabbitTemplate.convertAndSend(OrderServiceRabbitMQConfig.TOPIC_EXCHANGE, "ORDER_COMPANY", message);

        rabbitTemplate.convertAndSend(OrderServiceRabbitMQConfig.TOPIC_EXCHANGE, "ORDER_PRODUCT", message);
    }
    
    /*************** FANOUT EXCHANGE **********************************************/

    @PostMapping(value="/orderFanoutMessage", produces = "application/json", consumes= "application/json")
	public void customerRequest(@RequestBody MessageBody message)
	{
//		template.convertAndSend(CustomerRabbitMQConfig.EXCHANGE, CustomerRabbitMQConfig.ROUTING_KEYS, message);
	    rabbitTemplate.convertAndSend(OrderServiceRabbitMQConfig.FANOUT_EXCHANGE, "", message);

	}

    /*************** DIRECT EXCHANGE **********************************************/
	@PostMapping(value="/orderPersonalMessage/{consumer}", produces = "application/json", consumes= "application/json")
	public void orderPersonalMessage(@RequestBody com.rabbitMq.orderService.model.MessageBody message,@PathVariable("consumer") String consumer)
	{
		if("customer".equals(consumer))
		{
		
		rabbitTemplate.convertAndSend(OrderServiceRabbitMQConfig.DIRECT_EXCHANGE, OrderServiceRabbitMQConfig.DI_ROUTE_ORDER_CUSTOMER, message);
		}
		else if("product".equals(consumer))
		{
		rabbitTemplate.convertAndSend(OrderServiceRabbitMQConfig.DIRECT_EXCHANGE, OrderServiceRabbitMQConfig.DI_ROUTE_ORDER_PRODUCT, message);

		}
	}
}