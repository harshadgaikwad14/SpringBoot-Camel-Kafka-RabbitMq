package com.example.model;

import java.io.Serializable;

public class Payload<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1257498804519038782L;

	private long payloadId;

	private Object payloadData;
	
	private Object payloadContext;

	public long getPayloadId() {
		return payloadId;
	}

	public void setPayloadId(long payloadId) {
		this.payloadId = payloadId;
	}

	public Object getPayloadData() {
		return payloadData;
	}

	public void setPayloadData(Object payloadData) {
		this.payloadData = payloadData;
	}
	
	

	public Object getPayloadContext() {
		return payloadContext;
	}

	public void setPayloadContext(Object payloadContext) {
		this.payloadContext = payloadContext;
	}

	@Override
	public String toString() {
		return "Payload [payloadId=" + payloadId + ", payloadData=" + payloadData + ", payloadContext=" + payloadContext
				+ "]";
	}

	

}
