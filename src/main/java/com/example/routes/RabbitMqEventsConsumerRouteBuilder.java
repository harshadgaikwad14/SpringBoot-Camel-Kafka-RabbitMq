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
import com.example.exception.handler.DataNotFoundException;
import com.example.util.AppProperties;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Channel state events route builder.
 */
@Component
public class RabbitMqEventsConsumerRouteBuilder extends SpringRouteBuilder {

	/** App env properties. */
	@Autowired
	private AppProperties properties;

	/** The route id. */
	private static final String ROUTE_ID = "RABBITMQ_EVENT_CONSUMER";

	/**
	 * Route for channel state events.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public void configure() throws Exception {
		final String logLevel = properties.getCamelLogLevel();
		final String fqClassName = this.getClass().getName();

		// When enrichment data not found
		onException(DataNotFoundException.class).setHeader("rabbitmq.REQUEUE", constant(Boolean.TRUE));

		// For any other exception
		onException(Exception.class).setFaultBody(constant(true)).setHeader("rabbitmq.REQUEUE",
				constant(Boolean.FALSE));

		// Channel Events Route
		from(properties.getRmqConsumerEndpoint()).id(ROUTE_ID)
				// convert to json
				.unmarshal().json(JsonLibrary.Jackson, JsonNode.class)
				.to("log:" + fqClassName + "?showAll=true&level=" + logLevel)
				.bean(Bean1.class, "validateKafka")
				.bean(Bean2.class, "validateKafka")
				.choice()
					.when()
						.simple("${in.header.isContinue} == true", Boolean.class)
						.bean(Bean3.class,"validateKafka")
						.bean(Bean4.class, "validateKafka")
						.bean(Bean5.class, "validateKafka")
						.to("log:" + fqClassName + "?showAll=true&level=" + logLevel)
						.setExchangePattern(ExchangePattern.InOnly)
						.otherwise().to("log:" + fqClassName + "?showAll=true&level=" + logLevel)
				.setExchangePattern(ExchangePattern.InOnly)
				.log("*********** END Route Id : "+ROUTE_ID+" **********")
				.end();
	}

}
