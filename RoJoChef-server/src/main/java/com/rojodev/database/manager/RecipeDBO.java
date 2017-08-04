package com.rojodev.database.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.rojodev.models.Recipe;
import com.rojodev.utils.DatabaseUtils;

public class RecipeDBO {

	public static final String RECIPE_TABLE = "recipe";
	
	public static String insertRecipe(String id, Recipe recipe) throws JsonProcessingException {
		
		MongoCollection<Document> recipeTable = DatabaseFactory.getDatabaseManager().getDatabase().getCollection(RECIPE_TABLE);
		
		recipe.set_id(id);
		
		Document recipeDoc = DatabaseUtils.mapToDocument(recipe);
		
		recipeTable.insertOne(recipeDoc);
		
		String insertedId = recipeDoc.get("_id").toString();
		
		return insertedId;
	}
	
	public static void updateRecipe(Recipe updatedRecipe) throws JsonProcessingException {
		
		MongoCollection<Document> recipeTable = DatabaseFactory.getDatabaseManager().getDatabase().getCollection(RECIPE_TABLE);
		
		Document recipeDoc = DatabaseUtils.mapToDocument(updatedRecipe);
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", updatedRecipe.get_id());
		
		recipeTable.replaceOne(query, recipeDoc);
	}
	
	//TODO: This should do them in pages
	public static List<Recipe> getAllRecipes() throws JsonParseException, JsonMappingException, IOException {
		
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		MongoCollection<Document> recipeTable = DatabaseFactory.getDatabaseManager().getDatabase().getCollection(RECIPE_TABLE);
		MongoCursor<Document> cursor = recipeTable.find().iterator();
		
		ObjectMapper mapper = new ObjectMapper();

		while(cursor.hasNext()) {
			Document doc = cursor.next();
			Recipe recipe = mapper.readValue(doc.toJson(), Recipe.class);
			
			recipes.add(recipe);
		}
		
		return recipes;
	}
	
	public static Recipe getRecipe(String hexId) throws JsonParseException, JsonMappingException, IOException {
		MongoCollection<Document> recipeTable = DatabaseFactory.getDatabaseManager().getDatabase().getCollection(RECIPE_TABLE);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", hexId);
		Document result = recipeTable.find(query).first();
		
		ObjectMapper mapper = new ObjectMapper();
		Recipe recipe = mapper.readValue(result.toJson(), Recipe.class);
		
		return recipe;
	}
	
	
}
