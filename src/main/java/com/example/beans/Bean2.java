package com.example.beans;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class Bean2 {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Bean2.class);

	public void validateKafka(final JsonNode message, final Exchange exchange) {

		LOGGER.info("Entering validateKafka ... message : {} ",message);
		final String payloadId = message.get("payloadId").toString();
		LOGGER.info("Entering validateKafka ... payloadId : {} ",payloadId);
		final String payloadData = message.get("payloadData").toString();
		LOGGER.info("Entering validateKafka ... payloadData : {} ",payloadData);
		final String payloadContext = message.get("payloadContext").toString();
		LOGGER.info("Entering validateKafka ... payloadContext : {} ",payloadContext);
		boolean isBEStateEvt = false;
		
		if (payloadId.equalsIgnoreCase("101")) {
			exchange.setProperty("orignalMessage", message);
			isBEStateEvt = true;
		}
		exchange.getIn().setHeader("isBackendStateEvt", isBEStateEvt);
		LOGGER.info("Leaving validateKafka exchange -> {}", exchange.getMessage().toString());
	}

	

}
