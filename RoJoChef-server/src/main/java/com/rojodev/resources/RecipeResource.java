package com.rojodev.resources;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.rojodev.databasemanager.DatabaseFactory;
import com.rojodev.databasemanager.RoJoDatabaseManager;
import com.rojodev.models.Recipe;
import com.rojodev.utils.ResponseUtils;

@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {

	private static final Logger logger = LoggerFactory.getLogger(RecipeResource.class);
	
	@GET
	@Timed
	public Response getRecipes(@QueryParam("id") String hexId) throws IOException {
		
		if(StringUtils.isBlank(hexId)) {
			MongoCollection<Document> recipeTable = DatabaseFactory.getDatabaseManager().getDatabase().getCollection(RoJoDatabaseManager.RECIPE_TABLE);
			FindIterable<Document> cursor = recipeTable.find();
			return Response.ok(cursor).build();
		}
		else {
			MongoCollection<Document> recipeTable = DatabaseFactory.getDatabaseManager().getDatabase().getCollection(RoJoDatabaseManager.RECIPE_TABLE);
			BasicDBObject query = new BasicDBObject();
			query.put("_id", hexId);
			Document result = recipeTable.find(query).first();
			
			return Response.ok(result).build();
		}
	}
	
	@POST
	@Timed
	public Response addRecipe(Recipe recipe) {
		
		if(recipe == null) {
			logger.error("Unable to insert recipe, recipe may not be null.");
			return ResponseUtils.buildBadRequestResponse("Recipe may not be blank.");
		}
		else if(StringUtils.isBlank(recipe.getRecipeName())) {
			logger.error("Unable to insert recipe, recipe must include a name.");
			return ResponseUtils.buildBadRequestResponse("Recipe must include a name.");
		}
		else if(recipe.getIngredients() == null || recipe.getIngredients().size() == 0) {
			logger.error("Unable to insert recipe, recipe must include at least one ingredient.");
			return ResponseUtils.buildBadRequestResponse("Recipe must include at least one ingredient.");
		}
		else if(recipe.getDirections() == null || recipe.getDirections().size() == 0) {
			logger.error("Unable to insert recipe, recipe must include directions.");
			return ResponseUtils.buildBadRequestResponse("Recipe must include directions.");
		}
		
		ObjectId newId = new ObjectId();
		recipe.set_id(newId.toHexString());

		MongoCollection<Document> recipeTable = DatabaseFactory.getDatabaseManager().getDatabase().getCollection(RoJoDatabaseManager.RECIPE_TABLE);
		
		try {

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(recipe);
			
			Document recipeDoc = Document.parse(jsonString);
			recipeTable.insertOne(recipeDoc);
			
			String id = recipeDoc.get("_id").toString();
			return Response.ok(id).build();
			
		} 
		catch (JsonProcessingException e) {
			logger.error("Unable to parse recipe as json string", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e) {
			logger.error("Unknown exception caught while doing database insert", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
