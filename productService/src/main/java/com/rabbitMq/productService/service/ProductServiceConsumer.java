package com.rabbitMq.productService.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.stereotype.Component;
import com.rabbitMq.productService.config.ProductServiceRabbitMQConfig;
import com.rabbitMq.productService.model.OrderedRequest;

;

@Component
public class ProductServiceConsumer {
	@Autowired
	private ProductService productService;

//	@RabbitListener(queues= ProductServiceRabbitMQConfig.PRODUCT_QUEUE)
//	public void consumerCustomerOrderServiceQueue(OrderedRequest orderRequest )
//	{
//		
//		System.out.println(orderRequest.getOrderId());
//		System.out.println(orderRequest.getProductCode());
//		System.out.println(orderRequest.getQuantity());
//
//		productService.saveProductRequest(orderRequest);
//		
//	}

	/******************************************
	 * TOPIC EXCHANGE
	 ***************************************************/

	@RabbitListener(queues = { ProductServiceRabbitMQConfig.CUSTOMER_PRODUCT_QUEUE,
			ProductServiceRabbitMQConfig.ORDER_PRODUCT_QUEUE })
	public void messageQueueFromOrder(com.rabbitMq.productService.model.MessageBody messageBody) {

		System.out.println(messageBody.getMessage());

	}

	/******************************************
	 * DIRECT EXCHANGE
	 ***************************************************/

	@RabbitListener(queues = { ProductServiceRabbitMQConfig.DI_QUEUE_CUSTOMER_PRODUCT })
	public void custProdListen(com.rabbitMq.productService.model.MessageBody messageBody) {

		System.out.println(messageBody.getMessage());

	}

	@RabbitListener(queues = { ProductServiceRabbitMQConfig.DI_QUEUE_ORDER_PRODUCT })
	public void orderProdListen(com.rabbitMq.productService.model.MessageBody messageBody) {

		System.out.println(messageBody.getMessage());

	}

}
