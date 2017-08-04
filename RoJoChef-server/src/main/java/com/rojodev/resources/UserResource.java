package com.rojodev.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import com.codahale.metrics.annotation.Timed;
import com.rojodev.models.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@GET
	@Timed
	public Response getUser(@QueryParam("id") String hexId) {
		
		if(StringUtils.isBlank(hexId)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		return Response.ok().build();
	}
	
	@POST
	@Timed
	public Response registerUser(User user) {
		
		if(user == null) {
			
		}
		else if(StringUtils.isBlank(user.getEmail())) {
			
		}
		else if(StringUtils.isBlank(user.getFirstName()) || StringUtils.isBlank(user.getLastName())) {
			
		}
		
		return Response.ok().build();
	}
	
}
