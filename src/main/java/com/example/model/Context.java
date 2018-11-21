package com.example.model;

import java.io.Serializable;

public class Context implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7964879795177570356L;
	
	private long contextId;
	private String eventTime;
	private String eventType;
	private String eventStatus;
	public long getContextId() {
		return contextId;
	}
	public void setContextId(long contextId) {
		this.contextId = contextId;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	@Override
	public String toString() {
		return "Context [contextId=" + contextId + ", eventTime=" + eventTime + ", eventType=" + eventType
				+ ", eventStatus=" + eventStatus + "]";
	}
	
	
	
	
	

}
