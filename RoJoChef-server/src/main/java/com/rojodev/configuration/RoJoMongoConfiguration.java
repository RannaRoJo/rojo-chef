package com.rojodev.configuration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoJoMongoConfiguration {

	@NotEmpty
	@JsonProperty
	private String host;
	
	@Min(1)
	@Max(65535)
	@JsonProperty
	private int port = 27017;
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
	
}
