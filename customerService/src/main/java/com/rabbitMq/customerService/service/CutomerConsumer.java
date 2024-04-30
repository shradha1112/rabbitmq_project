package com.rabbitMq.customerService.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.rabbitMq.customerService.configuration.CustomerRabbitMQConfig;
import com.rabbitMq.customerService.model.MessageBody;

@Component
public class CutomerConsumer {

	
	//LISTENER FOR TOPIC EXCHANGE
	@RabbitListener(queues = { CustomerRabbitMQConfig.ORDER_CUSTOMER_QUEUE })
	public void messageQueueForCustomer(MessageBody messageBody) {

		System.out.println(messageBody.getMessage());

	}

	//LISTENER FOR TOPIC EXCHANGE
	@RabbitListener(queues = { CustomerRabbitMQConfig.PRODUCT_CUSTOMER_QUEUE })
	public void messageQueueForProducer(MessageBody messageBody) {

		System.out.println(messageBody.getMessage());

	}

	//LISTENER FOR DIRECT EXHANGE FROM ORDER
	@RabbitListener(queues = { CustomerRabbitMQConfig.DI_QUEUE_ORDER_CUSTOMER })
	public void orderCustListener(MessageBody messageBody) {

		System.out.println(messageBody.getMessage());

	}

}
