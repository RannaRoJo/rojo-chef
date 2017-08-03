package com.rojodev.application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rojodev.configuration.RoJoConfiguration;
import com.rojodev.databasemanager.DatabaseFactory;
import com.rojodev.resources.RecipeResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RoJoChefApplication extends Application<RoJoConfiguration> {

	private static final Logger logger = LoggerFactory.getLogger(RoJoChefApplication.class);
	
	public static void main(String[] args) throws Exception {
		try {
			new RoJoChefApplication().run(args);
		}
		catch(Exception e) {
			logger.error("Unknown exception while starting server: {}", e);
		}
	}
	
	@Override
	public void initialize(Bootstrap<RoJoConfiguration> bootstrap) {
		
	}
	
	@Override
	public void run(RoJoConfiguration configuration, Environment environment) throws Exception {
		final RecipeResource recipeResource = new RecipeResource();
		
		environment.jersey().register(recipeResource);
		environment.lifecycle().manage(DatabaseFactory.getDatabaseManager());
	}
	
}
