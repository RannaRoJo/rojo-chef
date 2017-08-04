package com.rojodev.database.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import io.dropwizard.lifecycle.Managed;

public class RoJoDatabaseManager implements Managed {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseFactory.class);
	
	private static MongoClient mongoClient;
	private static MongoDatabase database;

	protected RoJoDatabaseManager(String host, int port, String databaseName) {
		mongoClient = new MongoClient(host, port);
		database = mongoClient.getDatabase(databaseName);
		logger.info("Database has been connected");
	}
	
	//TODO make this extensible to create multiple database types
	public MongoDatabase getDatabase() {
		return  database;
	}

	public void start() throws Exception {
		//doing nothing right now
	}

	public void stop() throws Exception {
		if(mongoClient != null) {
			try {
				mongoClient.close();
				mongoClient = null;
				database = null;
				logger.info("Database has been shut down");
			}
			catch(Exception e) {
				logger.error("Database failed to shut down: {}", e);
			}
		}
	}
}
