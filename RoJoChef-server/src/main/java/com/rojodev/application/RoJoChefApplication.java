package com.rojodev.application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rojodev.configuration.RoJoConfiguration;
import com.rojodev.database.manager.DatabaseFactory;
import com.rojodev.resources.RecipeResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
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
		bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/", "index.html"));
	}
	
	@Override
	public void run(RoJoConfiguration configuration, Environment environment) throws Exception {
		final RecipeResource recipeResource = new RecipeResource();
		
		//connect the database
		DatabaseFactory.connectDatabase(configuration.getMongoConfiguration().getHost(), configuration.getMongoConfiguration().getPort());
		
		environment.jersey().register(recipeResource);
		
		//manage the database to ensure connections are closed
		environment.lifecycle().manage(DatabaseFactory.getDatabaseManager());
	}
	
}
