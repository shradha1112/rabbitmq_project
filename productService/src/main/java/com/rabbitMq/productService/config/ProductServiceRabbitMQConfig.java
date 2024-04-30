package com.rabbitMq.productService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceRabbitMQConfig {
	
	public static final String PRODUCT_QUEUE	= "product_Order_Service_Queue";
	public static final String PRODUCT_MESSAGE_QUEUE = "PRODUCT_MESSAGE_QUEUE";
	

	@Bean
	public MessageConverter converter() 
	{return new Jackson2JsonMessageConverter();
		
	}
	
	/******************************************TOPIC EXCHANGE ***************************************************/
	
	//TOPIC EXCHANGE
    public static final String TOPIC_EXCHANGE = "Customer_Topic_Exchange";
	//CONSUMER QUEUES
    public static final String CUSTOMER_PRODUCT_QUEUE = "CUSTOMER_PRODUCT_QUEUE";
    public static final String ORDER_PRODUCT_QUEUE = "ORDER_PRODUCT_QUEUE";

    public static final String BINDING_PATTERN_GROUP = "COMPANY.ALL.MEMBERS";
	//PRODUCER QUEUES
	public static final String PRODUCT_CUSTOMER_QUEUE = "PRODUCT_CUSTOMER_QUEUE";
	public static final String PRODUCT_ORDER_QUEUE = "PRODUCT_ORDER_QUEUE";

	//topic exchange code
		 @Bean
		    public TopicExchange topicExchange() {
		        return new TopicExchange(TOPIC_EXCHANGE);
		    }
		 

			@Bean
			public Queue OrderTopicQueue()
			{
				return new Queue(PRODUCT_ORDER_QUEUE);
				
			}

		
			@Bean
			public Queue CustomerTopicQueue()
			{
				return new Queue(PRODUCT_CUSTOMER_QUEUE);
				
			}
			@Bean
			public Binding bindingOrderTopicQueue()
			{
				return BindingBuilder.bind(OrderTopicQueue()).to(topicExchange()).with("PRODUCT_ORDER");
				
			}
			
			
			@Bean
			public Binding bindingProductTopicQueue()
			{
				return BindingBuilder.bind(CustomerTopicQueue()).to(topicExchange()).with("PRODUCT_COMPANY");
				
			}
			
			
			/******************************************DIRECT EXCHANGE ***************************************************/

			
			//consumers
			public static final String DI_QUEUE_CUSTOMER_PRODUCT = "DI_QUEUE_CUSTOMER_PRODUCT";
			public static final String DI_QUEUE_ORDER_PRODUCT = "DI_QUEUE_ORDER_PRODUCT";

}
