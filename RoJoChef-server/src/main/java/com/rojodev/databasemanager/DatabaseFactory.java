package com.rojodev.databasemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseFactory.class);
	private static final String host = "localhost";
	private static final int port = 27017;
	private static final String databaseName = "RoJoChef";
	
	private static RoJoDatabaseManager databaseManager;
	
	public static synchronized RoJoDatabaseManager getDatabaseManager() {
		if(databaseManager == null) {
			databaseManager = new RoJoDatabaseManager(host, port, databaseName);
		}		
		return databaseManager;
	}
	
	public static void stop() {
		try {
			databaseManager.stop();
		}
		catch(Exception e) {
			logger.error("DatabaseFactory failed to call stop on DatabaseManager: {}", e);
		}
		
	}
}
