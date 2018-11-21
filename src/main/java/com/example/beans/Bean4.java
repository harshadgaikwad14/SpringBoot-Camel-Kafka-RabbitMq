package com.example.beans;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class Bean4 {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Bean4.class);

	@Autowired
	private ObjectMapper objectMapper;

	public void validateKafka(final JsonNode message, final Exchange exchange) throws IOException {

		LOGGER.info("Entering validateKafka ... message : {} ", message);
		LOGGER.info("Entering validateKafka ... exchange : {} ", exchange);
		final String payloadId = message.get("payloadId").toString();
	
		LOGGER.info("Entering validateKafka ... payloadId : {} ", payloadId);

		final Object decision = exchange.getIn().getHeader("decision");
		LOGGER.info("Entering validateKafka ... decision : {} ", decision);

		final String payloadData = (message.get("payloadData") == null) ? null : message.get("payloadData").toString();

		LOGGER.info("Entering validateKafka ... payloadData : {} ", payloadData);

		final Person person = objectMapper.readValue(payloadData, Person.class);
		person.getPersonAddress().setZipCode("40009");
		final String personModified = objectMapper.writeValueAsString(person);

		ObjectNode node = (ObjectNode) message;
		node = (personModified == null) ? node : node.putPOJO("payloadData", objectMapper.readTree(personModified));

		exchange.getIn().setBody(node);

		LOGGER.info("Leaving validateKafka exchange -> {}", exchange.getMessage().toString());
	}

}
