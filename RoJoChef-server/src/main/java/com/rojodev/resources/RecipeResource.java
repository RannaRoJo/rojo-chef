package com.rojodev.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rojodev.database.manager.RecipeDBO;
import com.rojodev.models.Recipe;
import com.rojodev.utils.ResponseUtils;

@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {

	private static final Logger logger = LoggerFactory.getLogger(RecipeResource.class);
	
	@GET
	@Timed
	public Response getRecipes(@QueryParam("id") String hexId) {
		
		if(StringUtils.isBlank(hexId)) {
			
			List<Recipe> allRecipes;
			try {
				allRecipes = RecipeDBO.getAllRecipes();
			} catch (Exception e) {
				logger.error("Unable to get recipes from database.", e);
				return ResponseUtils.buildBadRequestResponse("Error finding recipes, please try again later.");
			}
			return Response.ok(allRecipes).build();
		}
		else {
			
			Recipe recipe;
			try {
				recipe = RecipeDBO.getRecipe(hexId);
			} catch (Exception e) {
				logger.error("Unable to get recipe {} from database.", hexId);
				e.printStackTrace();
				return ResponseUtils.buildBadRequestResponse("Error finding recipes, please try again later.");
			}
			return Response.ok(recipe).build();
		}
		
	}
	
	@PUT
	@Timed
	public Response updateRecipe(Recipe recipe) {
		if(recipe == null || StringUtils.isBlank(recipe.get_id())) {
			logger.error("Unable to update recipe, recipe may not be null.");
			return ResponseUtils.buildBadRequestResponse("Recipe may not be blank.");
		}
		else if(StringUtils.isBlank(recipe.getRecipeName())) {
			logger.error("Unable to update recipe, recipe must include a title.");
			return ResponseUtils.buildBadRequestResponse("Recipe must include a name.");
		}
		else if(recipe.getIngredients() == null || recipe.getIngredients().size() == 0) {
			logger.error("Unable to update recipe, recipe must include at least one ingredient.");
			return ResponseUtils.buildBadRequestResponse("Recipe must include at least one ingredient.");
		}
		else if(recipe.getDirections() == null || recipe.getDirections().size() == 0) {
			logger.error("Unable to update recipe, recipe must include directions.");
			return ResponseUtils.buildBadRequestResponse("Recipe must include directions.");
		}
		
		try {
			RecipeDBO.updateRecipe(recipe);
			return ResponseUtils.buildOkResponse(recipe);
		} catch (JsonProcessingException e) {
			logger.error("Unable to parse recipe as a JSON String.", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
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
			return ResponseUtils.buildBadRequestResponse("Recipe must include a title.");
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
		
		try {

			String returnedId = RecipeDBO.insertRecipe(newId.toHexString(), recipe);
			
			if(StringUtils.isNotBlank(returnedId)) {
				return Response.ok(returnedId).build();
			}
		} 
		catch (JsonProcessingException e) {
			logger.error("Unable to parse recipe as json string", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e) {
			logger.error("Unknown exception caught while doing database insert", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseUtils.buildBadRequestResponse("Unable to create recipe.");
	}
}
