package com.example.beans;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class Bean1 {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Bean1.class);

	
	private boolean isSkip;

	
	public void validateKafka(final JsonNode message, final Exchange exchange) {

		LOGGER.info("Entering validateKafka ... message : {} ",message);
		final String payloadId = message.get("payloadId").toString();
		LOGGER.info("Entering validateKafka ... payloadId : {} ",payloadId);
		

		if (payloadId==null) {
			skipEvent();
		}

		if (isSkip) {
			LOGGER.info("Skipping event : {}", payloadId);
			exchange.setPattern(ExchangePattern.InOnly);
			exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
			isSkip = false;
			return;
		}
		LOGGER.info("Leaving Validating SE Event -> {}", payloadId);
	}

	private void skipEvent() {
		this.isSkip = true;
	}

}
