package com.rabbitMq.customerService.configuration;

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
public class CustomerRabbitMQConfig {
	
	public static final String EXCHANGE = "Cust_Order_Prod_Exchange";
	public static final String ROUTING_KEYS = "customer_Order_Service_Routing_Key";
	public static final String QUEUE = "customer_Order_Service_Queue";




	//CONVERTER
	@Bean
	public MessageConverter converter() 
	{return new Jackson2JsonMessageConverter();
		
	}
	
   
	@Bean
	public TopicExchange exchange()
	{
		return new TopicExchange(EXCHANGE);
		
	}
	
//	@Bean
//	public Binding bindingCustomerServiceQueue(TopicExchange exchange)
//	{
//		return BindingBuilder.bind(customerOrderQueue()).to(exchange).with(ROUTING_KEYS);
//		
//	}
	/***************************** FANOUT EXCHANGE *****************************/
	
	

	  public static final String FANOUT_EXCHANGE = "Customer_Fanout_Exchange";
	  
	  //PRODUCER QUEUES
	  public static final String ORDER_MESSAGE_QUEUE = "ORDER_MESSAGE_QUEUE";
	  public static final String PRODUCT_MESSAGE_QUEUE = "PRODUCT_MESSAGE_QUEUE";
	  
	  
	  
	  @Bean
		public Queue OrderMessageQueue()
		{
			return new Queue(ORDER_MESSAGE_QUEUE);
			
		}

		@Bean
		public Queue ProductMessageQueue()
		{
			return new Queue(PRODUCT_MESSAGE_QUEUE);
			
		}
		
		 @Bean
		    public FanoutExchange fanoutExchange() {
		        return new FanoutExchange(FANOUT_EXCHANGE);
		    }

	    @Bean
	    public Binding bindingOrdertoFanoutExchange() {
	        return BindingBuilder.bind(OrderMessageQueue()).to(fanoutExchange());
	    }
	    
	    @Bean
	    public Binding bindingProducttoFanoutExchange() {
	        return BindingBuilder.bind(ProductMessageQueue()).to(fanoutExchange());
	    }

	
	/***************************** TOPIC EXCHANGE *****************************/
	
	//EXCHANGE
    public static final String TOPIC_EXCHANGE = "Customer_Topic_Exchange";

	//PRODUCER QUEUES
	public static final String CUSTOMER_ORDER_QUEUE = "CUSTOMER_ORDER_QUEUE";
	public static final String CUSTOMER_PRODUCT_QUEUE = "CUSTOMER_PRODUCT_QUEUE";
	
	//CONSUMER QUEUES
	public static final String ORDER_CUSTOMER_QUEUE = "ORDER_CUSTOMER_QUEUE";
	public static final String PRODUCT_CUSTOMER_QUEUE = "PRODUCT_CUSTOMER_QUEUE";

	
	 @Bean
	    public TopicExchange topicExchange() {
	        return new TopicExchange(TOPIC_EXCHANGE);
	    }
	 

		@Bean
		public Queue OrderTopicQueue()
		{
			return new Queue(CUSTOMER_ORDER_QUEUE);
			
		}

	
		@Bean
		public Queue ProductTopicQueue()
		{
			return new Queue(CUSTOMER_PRODUCT_QUEUE);
			
		}
		@Bean
		public Binding bindingOrderTopicQueue()
		{
			return BindingBuilder.bind(OrderTopicQueue()).to(topicExchange()).with("COMPANY_ORDER");
			
		}
		
		
		@Bean
		public Binding bindingProductTopicQueue()
		{
			return BindingBuilder.bind(ProductTopicQueue()).to(topicExchange()).with("COMPANY_PRODUCT");
			
		}
		
		
		/***************************** DIRECT EXCHANGE *****************************/
		
	    public static final String DIRECT_EXCHANGE = "DIRECT_EXCHANGE";

		//ROUNTING KEYS
		public static final String DI_ROUTE_CUSTOMER_ORDER = "DI_ROUTE_CUSTOMER_ORDER";
		public static final String DI_ROUTE_CUSTOMER_PRODUCT = "DI_ROUTE_CUSTOMER_PRODUCT";
		
		//PRODUCER QUESUES
		public static final String DI_QUEUE_CUSTOMER_ORDER = "DI_QUEUE_CUSTOMER_ORDER";
		public static final String DI_QUEUE_CUSTOMER_PRODUCT = "DI_QUEUE_CUSTOMER_PRODUCT";
		
		//CONSUMER QUESUES
		public static final String DI_QUEUE_ORDER_CUSTOMER = "DI_QUEUE_ORDER_CUSTOMER";
		public static final String DI_QUEUE_PRODUCT_CUSTOMER = "DI_QUEUE_PRODUCT_CUSTOMER";
		
		
		 @Bean
		    public DirectExchange directExchange() {
		        return new DirectExchange(DIRECT_EXCHANGE);
		    }
		 

			@Bean
			public Queue OrderDirectQueue()
			{
				return new Queue(DI_QUEUE_CUSTOMER_ORDER);
				
			}

		
			@Bean
			public Queue ProductDirectQueue()
			{
				return new Queue(DI_QUEUE_CUSTOMER_PRODUCT);
				
			}
			@Bean
			public Binding bindingOrderDirectQueue()
			{
				return BindingBuilder.bind(OrderDirectQueue()).to(directExchange()).with(DI_ROUTE_CUSTOMER_ORDER);
				
			}
			
			
			@Bean
			public Binding bindingProductDirectQueue()
			{
				return BindingBuilder.bind(ProductDirectQueue()).to(directExchange()).with(DI_ROUTE_CUSTOMER_PRODUCT);
				
			}
		    
	    
}
