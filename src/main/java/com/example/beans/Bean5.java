package com.example.beans;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
@Component
public class Bean5 {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Bean5.class);

	

	public void validateKafka(final JsonNode message, final Exchange exchange) throws IOException {

		LOGGER.info("Entering validateKafka ... message : {} ", message);
		LOGGER.info("Entering validateKafka ... exchange : {} ", exchange);
		final String payloadId = message.get("payloadId").toString();
	
		LOGGER.info("Entering validateKafka ... payloadId : {} ", payloadId);

		final Object decision = exchange.getIn().getHeader("decision");
		LOGGER.info("Entering validateKafka ... decision : {} ", decision);

		final String payloadData = (message.get("payloadData") == null) ? null : message.get("payloadData").toString();

		LOGGER.info("Entering validateKafka ... payloadData : {} ", payloadData);

		exchange.getIn().setBody(message);

		LOGGER.info("Leaving validateKafka exchange -> {}", exchange.getMessage().toString());
	}

}
