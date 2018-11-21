package com.example.routes;

import org.apache.camel.ExchangePattern;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.beans.Bean1;
import com.example.beans.Bean2;
import com.example.beans.Bean3;
import com.example.beans.Bean4;
import com.example.beans.Bean5;
import com.example.exception.handler.ErrorProcessor;
import com.example.util.AppProperties;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class KafkaEventsConsumerRouteBuilder extends SpringRouteBuilder {

	@Autowired
	private AppProperties properties;
	private static final String ROUTE_ID = "KAFKA_EVENT_CONSUMER";

	@Override
	public void configure() throws Exception {
		final String logLevel = properties.getCamelLogLevel();
		final String fqClassName = this.getClass().getName();

		// For invalid event state (retry)
		onException(Exception.class).setHeader("autoCommitEnable", constant(Boolean.FALSE));

		// For handling exception
		onException(Exception.class).setExchangePattern(ExchangePattern.InOnly).bean(ErrorProcessor.class, "handle")
				.handled(true);

		from(properties.getKafkaConsumerEndpoint())
				.id(ROUTE_ID)
				.unmarshal().json(JsonLibrary.Jackson, JsonNode.class)
				.to("log:" + fqClassName + "?showAll=true&level=" + logLevel)
				.bean(Bean1.class, "validateKafka")
				.bean(Bean2.class, "validateKafka")
				.choice()
					.when().simple("${in.header.isBackendStateEvt} == true",Boolean.class)
						.bean(Bean3.class,"validateKafka")
						.choice()
							.when().simple("${in.header.isContinue} == true",Boolean.class)
								.bean(Bean4.class, "validateKafka")
								.bean(Bean5.class, "validateKafka")
							.endChoice()
					.otherwise()
						.bean(Bean3.class,"validateKafka")
						.bean(Bean4.class, "validateKafka")
						.bean(Bean5.class, "validateKafka")
				.setExchangePattern(ExchangePattern.InOnly)
				.log("*********** END Route Id : "+ROUTE_ID+" **********")
				.end();

	}

}
