
package com.example.routes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.util.AppProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * This class contains methods to send invalid kafka messages to Dead Letter
 * Queue. DLQ would be a single topic across all applications
 *
 */
@Service
public class DLQProducer {

	/**  The constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DLQProducer.class);
	
	/**  Application properties. */
	@Autowired
	private AppProperties properties;

	/**  The camel context. */
	@Autowired
	private CamelContext context;
	

	/**
	 *  Sends the message to Dead Letter Queue for further analysis.
	 *
	 * @param exchange The Exchange object
	 */
	public void sendToDLQ(final Exchange exchange) {
		
		LOGGER.info("Record is being sent to Kafka Dead Letter Topic");
		final ProducerTemplate template = context.createProducerTemplate();
		// Create data for producer
		final JsonNode dataToSend = createDataForProducer(exchange);
		// Send message to DLQ topic asynchronously
		template.sendBody(properties.getKafkaDlqEndpoint(), dataToSend);
		LOGGER.info("Record sent to Kafka Dead Letter Topic successfully");
	}
	
	/**
	 *  Sends the message to Dead Letter Queue for further analysis.
	 *
	 * @param exchange The Exchange object
	 */
	public void sendToRMDLQ(final Exchange exchange) {
		
		LOGGER.info("Record is being sent to RabbitMQ Dead Letter Queue");
		final ProducerTemplate template = context.createProducerTemplate();
		final JsonNode recordData = (JsonNode)exchange.getIn().getBody();
		//essential header information
		final Map<String, Object> headers = new HashMap<>();
		headers.put("rabbitmq.ROUTING_KEY", properties.getRabbitDlRoutingKey());
		headers.put("rabbitmq.DELIVERY_MODE", Integer.valueOf(2)); // 2=persistent
		headers.put("rabbitmq.TIMESTAMP", new Date());	
		
		//Publish event with json header asynchronously (send and forget)
		template.sendBodyAndHeaders(properties.getRmqProducerEndpoint(), recordData.toString(), headers);
		LOGGER.info("Record sent to RabbitMQ Dead Letter Queue successfully");
	}

	/**
	 * Data is sent to DLQ only when the data is not in expected format. DLQ
	 * message should provide the following 1. Topic name 2. offset of the
	 * record 3. partition of the topic 4. message received 5. Consumer app name
	 * 6. Consumer group id
	 * 
	 * @param exchange
	 *            An Exchange is the message container holding the information
	 *            during the entire routing of a Message received by a Consumer.
	 * @return JsonNode The jsonNode
	 */
	private JsonNode createDataForProducer(final Exchange exchange) {
		final Message message = exchange.getIn();
		final Map<String, Object> headers = message.getHeaders();
		final ObjectNode recordForProducer = JsonNodeFactory.instance.objectNode();

		final NumericNode offset = createNumericNode(headers.get("kafka.OFFSET"));
		final NumericNode partition = createNumericNode(headers.get("kafka.PARTITION"));
		final TextNode topicName = createTextNode(headers.get("kafka.TOPIC"));
		final TextNode consumerAppName = createTextNode(properties.getModule());
		final TextNode consumerGroupId = createTextNode(properties.getGroupId());
		final TextNode receivedMessage = createTextNode(message.getBody());
		final TextNode errorType = createTextNode("DATA_INVALID");

		recordForProducer.set("consumerAppName", consumerAppName);
		recordForProducer.set("consumerGroupId", consumerGroupId);
		recordForProducer.set("offset", offset);
		recordForProducer.set("topicName", topicName);
		recordForProducer.set("partition", partition);
		recordForProducer.set("errorType", errorType);
		recordForProducer.set("receivedMessage", receivedMessage);
		return recordForProducer;
	}

	/**
	 * Create a text node from specified data.
	 *
	 * @param data            the data
	 * @return {@link TextNode} the node
	 */
	private static TextNode createTextNode(final Object data) {
		return JsonNodeFactory.instance.textNode(data.toString());
	}

	/**
	 * Create a numeric node from specified data.
	 *
	 * @param data            the data
	 * @return {@link NumericNode} the node
	 */
	private static NumericNode createNumericNode(final Object data) {
		final int num = Integer.valueOf(data.toString());
		return (NumericNode) JsonNodeFactory.instance.numberNode(num);
	}
}
