package com.example.config;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.routes.KafkaEventsConsumerRouteBuilder;
import com.example.routes.RabbitMqEventsConsumerRouteBuilder;
import com.example.util.AppContextProvider;

@Configuration
public class CamelContextConfig {

	
	 @Autowired
	private RabbitMqEventsConsumerRouteBuilder rabbitMqEventsConsumerRouteBuilder;


	@Autowired
	private KafkaEventsConsumerRouteBuilder kafkaEventsConsumerRouteBuilder;

	@Bean
	public SpringCamelContext camelContext() throws Exception {
		final SpringCamelContext camelContext = new SpringCamelContext(AppContextProvider.getApplicationContext());
		camelContext.addRoutes(rabbitMqEventsConsumerRouteBuilder);
		camelContext.addRoutes(kafkaEventsConsumerRouteBuilder);
		return camelContext;
	}

}
