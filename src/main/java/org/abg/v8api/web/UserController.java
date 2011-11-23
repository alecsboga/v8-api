package org.abg.v8api.web;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.abg.v8api.domain.User;
import org.abg.v8api.exception.ApplicationException;
import org.abg.v8api.exception.SystemException;
import org.abg.v8api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.core.HttpContext;

@Service
@Path("/")
public class UserController {

	private static final String PARAM_NAME = "name";

	private static final String PARAM_EMAIL = "email";

	@Autowired
	private UserService userService;

	@GET
	@Path("/echo")
	@Produces(MediaType.TEXT_PLAIN)
	public String echo(@QueryParam("message") String message) {
		return "echo=" + message;
	}

	@POST
	@Path("/user/create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(@Context HttpServletRequest httpServletRequest, @Context HttpContext httpContext,
	        MultivaluedMap<String, String> params) {

		Response response = Response.noContent().build();

		try {
			userService.add(getUser(params));
			response = Response.status(HttpURLConnection.HTTP_OK).type(MediaType.TEXT_PLAIN).build();
		} catch (SystemException e) {
			response = Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).type(MediaType.TEXT_PLAIN).build();
		} catch (ApplicationException e) {
			response = Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).type(MediaType.TEXT_PLAIN).build();
		}

		return response;
	}

	private User getUser(MultivaluedMap<String, String> params) {
		return new User(params.getFirst(PARAM_NAME), params.getFirst(PARAM_EMAIL));

	}
}
