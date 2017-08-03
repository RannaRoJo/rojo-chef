package com.rojodev.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class RoJoConfiguration extends Configuration {

	@Valid
	@NotNull
	@JsonProperty
	private RoJoMongoConfiguration mongoConfiguration = new RoJoMongoConfiguration();
	
	@NotEmpty
	private String template;
	@NotEmpty
	private String defaultName = "RoJo";
	
	public RoJoMongoConfiguration getMongoConfiguration() {
		return mongoConfiguration;
	}
	
	@JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}
