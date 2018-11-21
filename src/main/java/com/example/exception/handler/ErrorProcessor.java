package com.example.exception.handler;

import java.io.IOException;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.routes.DLQProducer;
import com.example.util.AppProperties;

public class ErrorProcessor {

	/** The DLQ producer. */
	@Autowired
	private DLQProducer dlqProducer;

	/** The constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorProcessor.class);

	/** App env properties. */
	@Autowired
	private AppProperties properties;

	/** The camel context. */
	@Autowired
	private CamelContext context;

	/** Message for parsing error. */
	private static final String PARSING_ERROR_MSG = "mapper_parsing_exception";

	public void handle(final Exception ex, final Exchange exchange) {
		LOGGER.debug("Exception bean called with {}", ex);
		final String errMsg = ex.getMessage();

		if (ex instanceof IOException) {
			if (isParsingError(errMsg)) {
				dlqProducer.sendToDLQ(exchange);
			} else {
				requeueEvent(exchange);
			}
		}
		/**
		 * Generic exception
		 */
		else {
			LOGGER.error("Sending to DLQ : \ncause {} ", errMsg);
			dlqProducer.sendToDLQ(exchange);
		}

	}

	private void requeueEvent(final Exchange exchange) {
		final ProducerTemplate template = context.createProducerTemplate();
		final Object body = exchange.getIn().getBody();
		try {
			Thread.sleep(properties.getEventRequeueDelay());
		} catch (InterruptedException ex) {
			LOGGER.error("Error occured while requeue : {}", ex.getMessage());
		}
		// send data to retry topic
		template.sendBody(properties.getKafkaProducerEndpoint(), body.toString());

	}

	private boolean isParsingError(final String error) {
		return error.contains(PARSING_ERROR_MSG);
	}
}
