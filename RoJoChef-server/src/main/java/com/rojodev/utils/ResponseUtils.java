package com.rojodev.utils;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ResponseUtils {

	public static Response buildErrorResponse(Status status, String errorMessage) {
		Map<String, String> errorResponse = new HashMap<String, String>();
		
		errorResponse.put("errorMessage", errorMessage);
		return Response.status(status).entity(errorResponse).build();
	}
	
	public static Response buildBadRequestResponse(String errorMessage) {
		return buildErrorResponse(Status.BAD_REQUEST, errorMessage);
	}
	
	public static Response buildServerErrorResponse(String errorMessage) {
		return buildErrorResponse(Status.INTERNAL_SERVER_ERROR, errorMessage);
	}
	
	public static Response buildOkResponse(Object payload) {
		return Response.ok(payload).build();
	}
}
