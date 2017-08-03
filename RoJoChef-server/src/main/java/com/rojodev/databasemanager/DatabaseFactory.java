package com.rojodev.databasemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseFactory.class);
	private static final String databaseName = "RoJoChef";
	
	private static RoJoDatabaseManager databaseManager;
	
	public static synchronized void connectDatabase(String host, int port) {
		if(databaseManager == null) {
			databaseManager = new RoJoDatabaseManager(host, port, databaseName);
		}
		else {
			throw new IllegalStateException("Database has already been connected. May only be connected once");
		}
	}
	
	public static synchronized RoJoDatabaseManager getDatabaseManager() {
		if(databaseManager == null) {
			throw new IllegalStateException("Database has not yet been connected. Please call connectDatabase to establish connection");
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
