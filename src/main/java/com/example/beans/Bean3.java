package com.example.beans;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.exception.handler.DataNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class Bean3 {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Bean3.class);

	public void validateKafka(final JsonNode message, final Exchange exchange) throws DataNotFoundException {

		LOGGER.info("Entering validateKafka ... message : {} ", message);
		LOGGER.info("Entering validateKafka ... exchange : {} ", exchange);

		LOGGER.info("Entering validateBean2 ... message : {} ", message);
		final String payloadId = message.get("payloadId").toString();
		LOGGER.info("Entering validateBean2 ... payloadId : {} ", payloadId);
		final String payloadData = message.get("payloadData").toString();
		LOGGER.info("Entering validateBean2 ... payloadData : {} ", payloadData);
		final JsonNode payloadContextNode = message.get("payloadContext");
		LOGGER.info("Entering validateBean2 ... payloadContextNode : {} ", payloadContextNode);
		// Skip the unwanted message
		if (payloadId == null) {

			LOGGER.info("Skipping message : {}", payloadId);
			exchange.setPattern(ExchangePattern.InOnly);
			exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
			return;
		}

		// Requeue the wanted message
		if (payloadData == null) {
			throw new DataNotFoundException("Data Not Found Exception for Id " + payloadId);
		}

		// Continue the message
		if (payloadId != null && payloadData != null) {
			final String eventType = payloadContextNode.get("eventType").textValue();
			final String eventStatus = payloadContextNode.get("eventStatus").textValue();
			if (eventStatus.equalsIgnoreCase("ACTIVE")) {
				exchange.getIn().setHeader("isContinue", true);

				if (eventType.equalsIgnoreCase("INSERT")) {
					exchange.getIn().setHeader("decision", "add");
				} else if (eventType.equalsIgnoreCase("UPDATE")) {
					exchange.getIn().setHeader("decision", "amend");
				} else {
					exchange.getIn().setHeader("decision", null);
				}

			}

		}

		LOGGER.info("Leaving validateKafka exchange -> {}", exchange.getMessage().toString());
	}

}
