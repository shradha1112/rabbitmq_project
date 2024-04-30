package com.rabbitMq.orderService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class OrderServiceRabbitMQConfig {
	
	public static final String QUEUE = "customer_Order_Service_Queue";
	public static final String EXCHANGE = "Cust_Order_Prod_Exchange";
	public static final String ROUTING_KEY = "product_Order_Service_Routing_Key";
	public static final String PRODUCT_QUEUE = "product_Order_Service_Queue";
	public static final String ORDER_MESSAGE_QUEUE = "ORDER_MESSAGE_QUEUE";
	

	
	@Bean
	public MessageConverter converter() 
	{return new Jackson2JsonMessageConverter();
		
	}
	
	@Bean
    public Queue productOrderQueue() {
        return new Queue(PRODUCT_QUEUE);
    }

	

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }


    @Bean
    public Binding bindingProductServiceQueue(TopicExchange exchange) {
        return BindingBuilder.bind(productOrderQueue()).to(exchange).with(ROUTING_KEY);
    }
    
	/**********************TOPIC EXCHANGE ***********************/

  
	//CONSUMER QUEUES
	public static final String CUSTOMER_ORDER_QUEUE = "CUSTOMER_ORDER_QUEUE";
	public static final String PRODUCT_ORDER_QUEUE = "PRODUCT_ORDER_QUEUE";
	
	public static final String BINDING_PATTERN_GROUP = "COMPANY.ALL.MEMBERS";
    public static final String TOPIC_EXCHANGE = "Customer_Topic_Exchange";
	//PRODUCER QUEUES
    public static final String ORDER_CUSTOMER_QUEUE = "ORDER_CUSTOMER_QUEUE";
	public static final String ORDER_PRODUCT_QUEUE = "ORDER_PRODUCT_QUEUE";

  	 @Bean
  	    public TopicExchange topicExchange() {
  	        return new TopicExchange(TOPIC_EXCHANGE);
  	    }
  	 

  		@Bean
  		public Queue custOrderTopicQueue()
  		{
  			return new Queue(ORDER_CUSTOMER_QUEUE);
  			
  		}

  	
  		@Bean
  		public Queue prodOrdertTopicQueue()
  		{
  			return new Queue(ORDER_PRODUCT_QUEUE);
  			
  		}
  		@Bean
  		public Binding bindingCustomerTopicQueue()
  		{
  			return BindingBuilder.bind(custOrderTopicQueue()).to(topicExchange()).with("ORDER_COMPANY");
  			
  		}
  		
  		
  		@Bean
  		public Binding bindingProductTopicQueue()
  		{
  			return BindingBuilder.bind(prodOrdertTopicQueue()).to(topicExchange()).with("ORDER_PRODUCT");
  			
  		}
  		    
  		
  		/********************** DIRECT EXCHANGE ***********************/

	    public static final String DIRECT_EXCHANGE = "DIRECT_EXCHANGE";
	    
  		// Consumer
		public static final String DI_QUEUE_CUSTOMER_ORDER = "DI_QUEUE_CUSTOMER_ORDER";
		public static final String DI_QUEUE_PRODUCT_ORDER = "DI_QUEUE_PRODUCT_ORDER";
		
		//PRODUCER
		public static final String DI_QUEUE_ORDER_CUSTOMER = "DI_QUEUE_ORDER_CUSTOMER";
		public static final String DI_QUEUE_ORDER_PRODUCT = "DI_QUEUE_ORDER_PRODUCT";
		
		//ROUNTING KEYS
		public static final String DI_ROUTE_ORDER_CUSTOMER = "DI_ROUTE_ORDER_CUSTOMER";
		public static final String DI_ROUTE_ORDER_PRODUCT = "DI_ROUTE_ORDER_PRODUCT";
		
		
		 @Bean
		    public DirectExchange directExchange() {
		        return new DirectExchange(DIRECT_EXCHANGE);
		    }
		 

			@Bean
			public Queue CustomerDirectQueue()
			{
				return new Queue(DI_QUEUE_ORDER_CUSTOMER);
				
			}

		
			@Bean
			public Queue ProductDirectQueue()
			{
				return new Queue(DI_QUEUE_ORDER_PRODUCT);
				
			}
			@Bean
			public Binding bindingCustomerDirectQueue()
			{
				return BindingBuilder.bind(CustomerDirectQueue()).to(directExchange()).with(DI_ROUTE_ORDER_CUSTOMER);
				
			}
			
			
			@Bean
			public Binding bindingProductDirectQueue()
			{
				return BindingBuilder.bind(ProductDirectQueue()).to(directExchange()).with(DI_ROUTE_ORDER_PRODUCT);
				
			}
    
			
	/**********************FANOUT EXCHANGE ***********************/
			
	public static final String FANOUT_EXCHANGE = "Customer_Fanout_Exchange";

	 @Bean
	    public FanoutExchange fanoutExchange() {
	        return new FanoutExchange(FANOUT_EXCHANGE);
	    }
	 
	 @Bean
		public Binding bindingCustomerFanoutQueue()
		{
			return BindingBuilder.bind(custOrderTopicQueue()).to(fanoutExchange());
			
		}
		
		
		@Bean
		public Binding bindingProductFanoutQueue()
		{
			return BindingBuilder.bind(prodOrdertTopicQueue()).to(fanoutExchange());
			
		}
	 
}
