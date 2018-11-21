package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCamelApplication.class, args);
	}

	/*
	 * Testing Payload For Kafka
	 * 
	 * D:\kafka_2.12-1.0.0>.\bin\windows\kafka-console-producer --broker-list
	 * localhost:9092 --topic LIQUIDITY-SWEEPS-OUTPUT-TOPIC
	 * 
	 * >{"payloadId":101,"payloadData":{"personId":101,"personName":"Harshad",
	 * "personGender":"male","personAddress":{"addressId":1,"city":"Mumbai",
	 * "country":"India","zipCode":"4
	 * 00065"}},"payloadContext":{"contextId":101,"eventTime":"Wed Nov 21 11:43:04
	 * IST 2018","eventType":"INSERT","eventStatus":"ACTIVE"}}
	 * 
	 * >{"payloadId":102,"payloadData":{"personId":102,"personName":"Vivek",
	 * "personGender":"male","personAddress":{"addressId":1,"city":"Mumbai",
	 * "country":"India","zipCode":"400
	 * 065"}},"payloadContext":{"contextId":102,"eventTime":"Wed Nov 21 11:43:04 IST
	 * 2018","eventType":"INSERT","eventStatus":"ACTIVE"}} 
	 * 
	 * 
	 * Testing Payload For RabbitMq
	 * 
	 * http://localhost:15672/
	 * 
	 * UserName :guest Password : guest
	 * 
	 * Exchange: cbxevents
	 * 
	 * Routing key: business.*.*.*.plain
	 * 
	 * Payload:
	 * {"payloadId":102,"payloadData":{"personId":102,"personName":"Vivek",
	 * "personGender":"male","personAddress":{"addressId":1,"city":"Mumbai",
	 * "country":"India","zipCode":"400
	 * 065"}},"payloadContext":{"contextId":102,"eventTime":"Wed Nov 21 11:43:04 IST
	 * 2018","eventType":"INSERT","eventStatus":"ACTIVE"}} 
	 */
}
