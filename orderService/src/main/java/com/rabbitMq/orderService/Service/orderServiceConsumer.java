package com.rabbitMq.orderService.Service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitMq.orderService.config.OrderServiceRabbitMQConfig;
import com.rabbitMq.orderService.model.CustomerRequest;
import com.rabbitMq.orderService.model.MessageBody;

@Component
public class orderServiceConsumer {

	@Autowired
	private OrderService orderService;

//	@RabbitListener(queues=OrderServiceRabbitMQConfig.QUEUE)
//	public void consumerCustomerOrderServiceQueue(CustomerRequest customerRequest)
//	{
//		System.out.println(customerRequest.getCustomerName());
//		System.out.println(customerRequest.getCustomerCity());
//		System.out.println(customerRequest.getCustomerMobile());
//
//		orderService.saveCustomerRequest(customerRequest);
//	}

	/*************** TOPIC EXCHANGE **********************************************/

	@RabbitListener(queues = { OrderServiceRabbitMQConfig.CUSTOMER_ORDER_QUEUE,
			OrderServiceRabbitMQConfig.PRODUCT_ORDER_QUEUE })
	public void messageQueue(MessageBody messageBody) {

		System.out.println(messageBody.getMessage());

	}

    /*************** DIRECT EXCHANGE **********************************************/

	@RabbitListener(queues = OrderServiceRabbitMQConfig.DI_QUEUE_CUSTOMER_ORDER)
	public void orderCustListen(MessageBody messageBody) {

		System.out.println(messageBody.getMessage());

	}
}