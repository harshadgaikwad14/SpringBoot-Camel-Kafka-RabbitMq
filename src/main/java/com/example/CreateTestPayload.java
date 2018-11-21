package com.example;

import java.io.IOException;
import java.util.Date;

import com.example.model.Address;
import com.example.model.Context;
import com.example.model.Payload;
import com.example.model.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateTestPayload {
	
	public static Payload<Person> createPayload()
	{
		final Address address= new Address();
		address.setAddressId(1);
		address.setCity("Mumbai");
		address.setCountry("India");
		address.setZipCode("400065");
		
		
		
		final  Person person= new Person();
		person.setPersonId(101);
		person.setPersonName("Harshad");
		person.setPersonGender("male");
		person.setPersonAddress(address);
		
		
		final Context context = new Context();
		context.setContextId(101);
		context.setEventStatus("ACTIVE");
		context.setEventTime(new Date().toString());
		context.setEventType("INSERT");
		
		final Payload<Person> payload = new Payload<>();
		payload.setPayloadId(101);
		payload.setPayloadData(person);
		payload.setPayloadContext(context);
		
		return payload;
		
	}
	

	public static void main(String[] args) throws IOException {
		
		Payload<Person> p = createPayload();
		
		ObjectMapper mapper = new ObjectMapper(); 
		String json = mapper.writeValueAsString(p);
		final JsonNode jsonNode = mapper.readTree(json);
		
		System.out.println("jsonNode : "+jsonNode);
		String payloadId= jsonNode.get("payloadId").toString();
		System.out.println(payloadId);
		JsonNode payloadDataNode= jsonNode.get("payloadData");
		System.out.println("payloadDataNode : "+payloadDataNode);
		String personId= payloadDataNode.get("personId").toString();
		System.out.println("personId : "+personId);
		String personName= payloadDataNode.get("personName").textValue();
		System.out.println("personName : "+personName);
		
		
		

	}

}
