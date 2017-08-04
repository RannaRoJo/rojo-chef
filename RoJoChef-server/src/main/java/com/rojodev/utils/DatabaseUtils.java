package com.rojodev.utils;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DatabaseUtils {

	public static Document mapToDocument(Object object) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(object);
		
		Document document = Document.parse(jsonString);
		
		return document;
	}
	 
}
